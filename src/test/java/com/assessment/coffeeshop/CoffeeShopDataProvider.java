package com.assessment.coffeeshop;

import com.assessment.coffeeshop.data.dto.*;
import com.assessment.coffeeshop.data.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CoffeeShopDataProvider {

    public static Menu getMenuEntity() {

        Menu menu = new Menu();
        menu.setMenuId(1);
        menu.setName("Coffee Shop Test Menu");
        menu.setStartDate(LocalDate.of(2023, 1, 1));
        menu.setEndDate(LocalDate.of(2024, 1, 1));

        menu.addMenuItems(getMenuItem(menu));
        return menu;
    }

    public static com.assessment.coffeeshop.data.entity.MenuItem getMenuItem(Menu menu) {

        com.assessment.coffeeshop.data.entity.MenuItem menuItem = new com.assessment.coffeeshop.data.entity.MenuItem();
        menuItem.setMenuItemId(1);
        menuItem.setMenu(menu);
        menuItem.setName("Hot chocolate");
        menuItem.setQuantity(10);
        menuItem.setCategory(new MenuItemCategory("specials", "Specials"));
        menuItem.setPrice(BigDecimal.valueOf(900.0000));
        return menuItem;
    }

    public static com.assessment.coffeeshop.data.entity.MenuItem getMenuItemWithNoQuantity() {

        com.assessment.coffeeshop.data.entity.MenuItem menuItem = new com.assessment.coffeeshop.data.entity.MenuItem();
        menuItem.setMenuItemId(1);
        menuItem.setName("Hot chocolate");
        menuItem.setQuantity(0);
        menuItem.setCategory(new MenuItemCategory("specials", "Specials"));
        menuItem.setPrice(BigDecimal.valueOf(900.0000));
        return menuItem;
    }

    public static MenuInfo getMenuInfo() {
        MenuInfo menuInfo = new MenuInfo();
        menuInfo.addMenuDetailsItem(getMenuDetails());
        return menuInfo;
    }

    private static MenuDetail getMenuDetails() {
        MenuDetail menuDetail = new MenuDetail();
        menuDetail.setMenuId(1);
        menuDetail.setMenuName("Coffee Shop Test Menu");
        menuDetail.setStartDate(LocalDate.of(2023, 1, 1));
        menuDetail.setEndDate(LocalDate.of(2024, 1, 1));
        menuDetail.addMenuItemsItem(getMenuItem());
        return menuDetail;
    }

    public static com.assessment.coffeeshop.data.dto.MenuItem getMenuItem() {

        com.assessment.coffeeshop.data.dto.MenuItem menuItem
                = new com.assessment.coffeeshop.data.dto.MenuItem();
        menuItem.setMenuItemId(1);
        menuItem.setMenuItemName("Hot chocolate");
        menuItem.setQuantity(10);
        menuItem.setCategory(com.assessment.coffeeshop.data.dto.MenuItem.CategoryEnum.CLASSIC);
        menuItem.setPrice(BigDecimal.valueOf(900.0000));
        return menuItem;

    }

    public static OrderRequest getOrderRequest() {

        OrderRequest request = new OrderRequest();
        request.setCustomerId("customer1");
        request.addMenuItemsItem(getMenuItemRequest());
        return request;

    }

    public static MenuItemRequest getMenuItemRequest() {

        MenuItemRequest menuItemRequest = new MenuItemRequest();
        menuItemRequest.setMenuItemId(1);
        menuItemRequest.setQuantity(10);
        return menuItemRequest;

    }

    public static OrderInfo getOrderInfo() {

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(1);
        orderInfo.setStatus(OrderInfo.StatusEnum.PLACED);
        orderInfo.setCustomerId("customer1");
        return orderInfo;

    }

    public static Customer getCustomer() {

        Customer customer = new Customer();
        customer.setCustomerId("customer1");
        customer.setEmail("customer@gmail.com");
        customer.setFullName("Test Customer");
        customer.setPhoneNumber("+923344667788");
        return customer;

    }

    public static SalesOrder getSalesOrder() {

        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setOrderId(1);
        salesOrder.setDate(LocalDate.now());
        salesOrder.setOrderStatus(new OrderStatus("placed", "Placed"));
        salesOrder.setTotalAmount(BigDecimal.TEN);
        salesOrder.addOrderItems(getOrderItem());
        salesOrder.setCustomer(getCustomer());
        return salesOrder;

    }

    public static OrderItem getOrderItem() {

        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(10);
        orderItem.setOrderItemId(1);
        orderItem.setPrice(BigDecimal.ONE);
        orderItem.setMenuItem(getMenuItem(null));
        return orderItem;

    }

}
