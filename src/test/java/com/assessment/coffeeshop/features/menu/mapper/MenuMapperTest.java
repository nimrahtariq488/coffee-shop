package com.assessment.coffeeshop.features.menu.mapper;

import com.assessment.coffeeshop.CoffeeShopDataProvider;
import com.assessment.coffeeshop.data.dto.MenuDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MenuMapperTest {


    @InjectMocks
    private MenuMapperImpl menuMapper;

    @Test
    void test_map_success() {

        List<MenuDetail> menuDetails = menuMapper.map(
                Arrays.asList(CoffeeShopDataProvider.getMenuEntity()));

        Assertions.assertNotNull(menuDetails);
        Assertions.assertEquals(1, menuDetails.size());
        Assertions.assertEquals(1, menuDetails.get(0).getMenuId());
        Assertions.assertEquals("Coffee Shop Test Menu", menuDetails.get(0).getMenuName());
        Assertions.assertNotNull(menuDetails.get(0).getMenuItems());
        Assertions.assertEquals(1, menuDetails.get(0).getMenuItems().get(0).getMenuItemId());
        Assertions.assertEquals("Hot chocolate", menuDetails.get(0).getMenuItems().get(0).getMenuItemName());
        Assertions.assertEquals(10, menuDetails.get(0).getMenuItems().get(0).getQuantity());
        Assertions.assertNull(menuDetails.get(0).getMenuItems().get(0).getCategory());
        Assertions.assertEquals(BigDecimal.valueOf(900.0), menuDetails.get(0).getMenuItems().get(0).getPrice());

    }

}
