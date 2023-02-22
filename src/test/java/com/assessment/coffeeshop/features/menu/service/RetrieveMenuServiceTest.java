package com.assessment.coffeeshop.features.menu.service;

import com.assessment.coffeeshop.CoffeeShopDataProvider;
import com.assessment.coffeeshop.data.dto.MenuInfo;
import com.assessment.coffeeshop.data.dto.MenuItem;
import com.assessment.coffeeshop.data.repository.MenuRepository;
import com.assessment.coffeeshop.exception.CoffeeShopException;
import com.assessment.coffeeshop.features.menu.mapper.MenuMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class RetrieveMenuServiceTest {

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private MenuMapper menuMapper;

    @InjectMocks
    private RetrieveMenuService retrieveMenuService;

    @Test
    void test_retrieveMenu_success() {

        Mockito.when(menuRepository.findAll())
                .thenReturn(Arrays.asList(CoffeeShopDataProvider.getMenuEntity()));
        Mockito.when(menuMapper.map(any()))
                .thenReturn(CoffeeShopDataProvider.getMenuInfo().getMenuDetails());

        MenuInfo menuInfo = retrieveMenuService.getMenu();

        Assertions.assertNotNull(menuInfo);
        Assertions.assertNotNull(menuInfo.getMenuDetails());
        Assertions.assertEquals(1, menuInfo.getMenuDetails().size());
        Assertions.assertEquals(1, menuInfo.getMenuDetails().get(0).getMenuId());
        Assertions.assertEquals("Coffee Shop Test Menu", menuInfo.getMenuDetails().get(0).getMenuName());
        Assertions.assertNotNull(menuInfo.getMenuDetails().get(0).getMenuItems());
        Assertions.assertEquals(1, menuInfo.getMenuDetails().get(0).getMenuItems().get(0).getMenuItemId());
        Assertions.assertEquals("Hot chocolate", menuInfo.getMenuDetails().get(0).getMenuItems().get(0).getMenuItemName());
        Assertions.assertEquals(10, menuInfo.getMenuDetails().get(0).getMenuItems().get(0).getQuantity());
        Assertions.assertEquals(MenuItem.CategoryEnum.CLASSIC, menuInfo.getMenuDetails().get(0).getMenuItems().get(0).getCategory());
        Assertions.assertEquals(BigDecimal.valueOf(900.0), menuInfo.getMenuDetails().get(0).getMenuItems().get(0).getPrice());

    }

    @Test
    void test_retrieveMenu_failure() {

        Assertions.assertThrows(CoffeeShopException.class, () -> {
            Mockito.when(menuRepository.findAll()).thenReturn(Collections.emptyList());
            retrieveMenuService.getMenu();
        });
    }

}
