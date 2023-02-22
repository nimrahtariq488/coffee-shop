package com.assessment.coffeeshop.features.order.service;

import com.assessment.coffeeshop.CoffeeShopDataProvider;
import com.assessment.coffeeshop.data.dto.OrderInfo;
import com.assessment.coffeeshop.data.dto.OrderRequest;
import com.assessment.coffeeshop.data.repository.CustomerRepository;
import com.assessment.coffeeshop.data.repository.MenuItemRepository;
import com.assessment.coffeeshop.data.repository.SalesOrderRepository;
import com.assessment.coffeeshop.exception.CoffeeShopException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PlaceOrderServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private SalesOrderRepository salesOrderRepository;

    @InjectMocks
    private PlaceOrderService placeOrderService;

    @Test
    void test_placeOrder_success() {

        Mockito.when(customerRepository.findById(anyString()))
                .thenReturn(Optional.of(CoffeeShopDataProvider.getCustomer()));
        Mockito.when(menuItemRepository.findByMenuItemIdIn(anyList()))
                .thenReturn(Arrays.asList(CoffeeShopDataProvider.getMenuItem(null)));
        Mockito.when(salesOrderRepository.save(any()))
                .thenReturn(CoffeeShopDataProvider.getSalesOrder());

        OrderInfo orderInfo = placeOrderService.placeOrder(CoffeeShopDataProvider.getOrderRequest());

        Assertions.assertNotNull(orderInfo);
        Assertions.assertEquals(1, orderInfo.getOrderId());
        Assertions.assertEquals("customer1", orderInfo.getCustomerId());
        Assertions.assertEquals(OrderInfo.StatusEnum.PLACED, orderInfo.getStatus());

    }

    @Test
    void test_retrieveMenu_failure_no_customer_found() {

        Assertions.assertThrows(CoffeeShopException.class, () -> {
            Mockito.when(customerRepository.findById(anyString())).thenReturn(Optional.empty());
            placeOrderService.placeOrder(CoffeeShopDataProvider.getOrderRequest());

        });
    }

    @Test
    void test_retrieveMenu_failure_menu_item_not_found() {

        Assertions.assertThrows(CoffeeShopException.class, () -> {
            Mockito.when(customerRepository.findById(anyString()))
                    .thenReturn(Optional.of(CoffeeShopDataProvider.getCustomer()));
            Mockito.when(menuItemRepository.findByMenuItemIdIn(anyList()))
                    .thenReturn(Collections.emptyList());
            placeOrderService.placeOrder(CoffeeShopDataProvider.getOrderRequest());
        });

    }

    @Test
    void test_retrieveMenu_failure_insufficient_quantity() {

        Assertions.assertThrows(CoffeeShopException.class, () -> {
            Mockito.when(customerRepository.findById(anyString()))
                    .thenReturn(Optional.of(CoffeeShopDataProvider.getCustomer()));
            Mockito.when(menuItemRepository.findByMenuItemIdIn(anyList()))
                    .thenReturn(Arrays.asList(CoffeeShopDataProvider.getMenuItemWithNoQuantity()));

            placeOrderService.placeOrder(CoffeeShopDataProvider.getOrderRequest());
        });

    }

    @Test
    void test_retrieveMenu_failure_empty_request_menu_item_list() {

        Assertions.assertThrows(CoffeeShopException.class, () -> {
            OrderRequest request = CoffeeShopDataProvider.getOrderRequest();
            request.setMenuItems(null);
            placeOrderService.placeOrder(request);
        });

    }

    @Test
    void test_retrieveMenu_failure_repeated_menu_item_list() {

        Assertions.assertThrows(CoffeeShopException.class, () -> {
            Mockito.when(customerRepository.findById(anyString()))
                    .thenReturn(Optional.of(CoffeeShopDataProvider.getCustomer()));
            OrderRequest request = CoffeeShopDataProvider.getOrderRequest();
            request.addMenuItemsItem(CoffeeShopDataProvider.getMenuItemRequest());
            placeOrderService.placeOrder(request);
        });

    }

}
