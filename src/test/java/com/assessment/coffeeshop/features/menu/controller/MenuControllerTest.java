package com.assessment.coffeeshop.features.menu.controller;


import com.assessment.coffeeshop.CoffeeShopDataProvider;
import com.assessment.coffeeshop.exception.CoffeeShopException;
import com.assessment.coffeeshop.features.menu.service.RetrieveMenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MenuController.class)
public class MenuControllerTest {

    @MockBean
    private RetrieveMenuService retrieveMenuService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_returnMenu_success() throws Exception {

        when(retrieveMenuService.getMenu()).thenReturn(CoffeeShopDataProvider.getMenuInfo());
        mockMvc.perform(get("/api/v1/menu"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.menuDetails[0].menuId").value(1))
                .andExpect(jsonPath("$.menuDetails[0].menuName").value("Coffee Shop Test Menu"))
                .andExpect(jsonPath("$.menuDetails[0].startDate").value("2023-01-01"))
                .andExpect(jsonPath("$.menuDetails[0].endDate").value("2024-01-01"))
                .andExpect(jsonPath("$.menuDetails[0].menuItems[0].menuItemId").value(1))
                .andExpect(jsonPath("$.menuDetails[0].menuItems[0].menuItemName").value("Hot chocolate"))
                .andExpect(jsonPath("$.menuDetails[0].menuItems[0].quantity").value(10))
                .andExpect(jsonPath("$.menuDetails[0].menuItems[0].price").value(BigDecimal.valueOf(900.0000)))
                .andExpect(jsonPath("$.menuDetails[0].menuItems[0].category").value("classic"));
    }

    @Test
    void test_returnMenu_error() throws Exception {

        when(retrieveMenuService.getMenu()).thenThrow(new CoffeeShopException("404", "Menu not found!"));
        mockMvc.perform(get("/api/v1/menu"))
                .andExpect(status().is4xxClientError());
    }

}