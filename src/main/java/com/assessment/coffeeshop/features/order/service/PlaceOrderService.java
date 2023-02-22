package com.assessment.coffeeshop.features.order.service;

import com.assessment.coffeeshop.data.dto.MenuItemDto;
import com.assessment.coffeeshop.data.dto.MenuItemRequest;
import com.assessment.coffeeshop.data.dto.OrderInfo;
import com.assessment.coffeeshop.data.dto.OrderRequest;
import com.assessment.coffeeshop.data.entity.*;
import com.assessment.coffeeshop.data.repository.CustomerRepository;
import com.assessment.coffeeshop.data.repository.MenuItemRepository;
import com.assessment.coffeeshop.data.repository.SalesOrderRepository;
import com.assessment.coffeeshop.exception.CoffeeShopException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PlaceOrderService {

    private final CustomerRepository customerRepository;
    private final MenuItemRepository menuItemRepository;
    private final SalesOrderRepository salesOrderRepository;

    /**
     * Class constructor to inject dependencies.
     */
    public PlaceOrderService(CustomerRepository customerRepository, MenuItemRepository menuItemRepository, SalesOrderRepository salesOrderRepository) {
        this.customerRepository = customerRepository;
        this.menuItemRepository = menuItemRepository;
        this.salesOrderRepository = salesOrderRepository;
    }

    /**
     * Place order.
     *
     * @param orderRequest the order request
     * @return orderInfo
     */
    @Transactional
    public OrderInfo placeOrder(OrderRequest orderRequest) {
        validateOrderRequest(orderRequest);
        return processOrderRequest(orderRequest);
    }

    /**
     * Process order request.
     */
    private OrderInfo processOrderRequest(OrderRequest orderRequest) {

        Map<Integer, MenuItemDto> menuItemMap = orderRequest.getMenuItems().stream()
                .collect(Collectors.toMap(MenuItemRequest::getMenuItemId,
                        menuItemRequest -> new MenuItemDto(menuItemRequest.getQuantity(), null)));

        List<MenuItem> menuItems = findMenuItems(menuItemMap);

        menuItems.forEach(menuItem -> {
            MenuItemDto itemDto = menuItemMap.get(menuItem.getMenuItemId());
            itemDto.setMenuItem(menuItem);

            if (itemDto.getQuantity() > menuItem.getQuantity()) {
                log.error("Menu item {} with id {} has only {} stocks left",
                        menuItem.getName(), menuItem.getMenuItemId(), menuItem.getQuantity());
                throw new CoffeeShopException("404", String.format("Insufficient quantity of item id %d: %s!",
                        menuItem.getMenuItemId(), menuItem.getName()));
            }
            menuItem.setQuantity(menuItem.getQuantity() - itemDto.getQuantity());
            menuItemMap.put(menuItem.getMenuItemId(), itemDto);
        });

        SalesOrder newSalesOrder = salesOrderRepository.save(
                prepareSalesOrder(orderRequest.getCustomerId(), menuItems, menuItemMap));

        menuItemRepository.saveAll(menuItems);

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(newSalesOrder.getOrderId());
        orderInfo.setCustomerId(newSalesOrder.getCustomer().getCustomerId());
        orderInfo.setStatus(OrderInfo.StatusEnum.PLACED);

        log.info("Order placed successfully for customer {} with order id {}",
                orderRequest.getCustomerId(), newSalesOrder.getOrderId());

        return orderInfo;

    }

    /**
     * Prepare sales order.
     */
    private SalesOrder prepareSalesOrder(String customerId,
                                         List<MenuItem> menuItems,
                                         Map<Integer, MenuItemDto> menuItemMap) {

        Customer customer = new Customer();
        customer.setCustomerId(customerId);

        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setCustomer(customer);
        salesOrder.setDate(LocalDate.now());
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderStatusId(OrderInfo.StatusEnum.PLACED.toString());
        salesOrder.setOrderStatus(orderStatus);
        salesOrder.setTotalAmount(BigDecimal.ZERO);

        menuItems.forEach(menuItem -> {

            Integer quantity = menuItemMap.get(menuItem.getMenuItemId()).getQuantity();

            OrderItem orderItem = new OrderItem();
            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(quantity);
            orderItem.setPrice(menuItemMap.get(menuItem.getMenuItemId()).getMenuItem().getPrice()
                    .multiply(BigDecimal.valueOf(quantity)));

            salesOrder.setTotalAmount(salesOrder.getTotalAmount().add(orderItem.getPrice()));
            salesOrder.addOrderItems(orderItem);
        });

        return salesOrder;
    }

    /**
     * Find menu items.
     */
    private List<MenuItem> findMenuItems(Map<Integer, MenuItemDto> menuItemMap) {

        List<MenuItem> menuItems = menuItemRepository.findByMenuItemIdIn(
                new ArrayList<>(menuItemMap.keySet()));

        if (menuItems.size() != menuItemMap.keySet().size()) {
            log.error("One of the menu item not found in the menu [{}]", menuItemMap.keySet());
            throw new CoffeeShopException("404", "Menu item not found!");
        }

        return menuItems;
    }

    /**
     * Validate order request.
     */
    private void validateOrderRequest(OrderRequest orderRequest) {

        if (ObjectUtils.isEmpty(orderRequest.getMenuItems())) {
            log.error("Menu item request list is empty");
            throw new CoffeeShopException("400", "There should be at least one menu item in the request!");
        }

        Optional<Customer> customer = customerRepository.findById(orderRequest.getCustomerId());
        if (customer.isEmpty()) {
            log.error("No customer found with customer id {}", orderRequest.getCustomerId());
            throw new CoffeeShopException("404", "Customer not found!");
        }

        if (orderRequest.getMenuItems().size() != orderRequest.getMenuItems().
                stream().map(MenuItemRequest::getMenuItemId).distinct().count()) {
            log.error("Menu item ids should be distinct in order request list");
            throw new CoffeeShopException("400", "Menu item ids should be distinct in order request list!");
        }

    }

}
