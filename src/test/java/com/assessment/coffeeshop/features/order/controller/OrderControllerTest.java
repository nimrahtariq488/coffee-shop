package com.assessment.coffeeshop.features.order.controller;


import com.assessment.coffeeshop.CoffeeShopDataProvider;
import com.assessment.coffeeshop.exception.CoffeeShopException;
import com.assessment.coffeeshop.features.order.service.PlaceOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @MockBean
    private PlaceOrderService placeOrderService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_placeOrder_success() throws Exception {


        when(placeOrderService.placeOrder(any())).thenReturn(CoffeeShopDataProvider.getOrderInfo());
        mockMvc.perform(post("/api/v1/order")
                        .content("{" +
                                "  \"customerId\": \"customer1\"," +
                                "  \"menuItems\": [" +
                                "    {" +
                                "      \"menuItemId\": 1," +
                                "      \"quantity\": 10" +
                                "    }" +
                                "  ]" +
                                "}")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(1))
                .andExpect(jsonPath("$.status").value("placed"))
                .andExpect(jsonPath("$.customerId").value("customer1"));
    }

    @Test
    void test_returnMenu_error() throws Exception {

        when(placeOrderService.placeOrder(any())).thenThrow(new CoffeeShopException("404", "Menu item not found!"));
        mockMvc.perform(post("/api/v1/order")
                        .content("{" +
                                "  \"customerId\": \"customer1\"," +
                                "  \"menuItems\": [" +
                                "    {" +
                                "      \"menuItemId\": 100," +
                                "      \"quantity\": 10" +
                                "    }" +
                                "  ]" +
                                "}")
                        .contentType("application/json"))
                .andExpect(status().is4xxClientError());
    }

}