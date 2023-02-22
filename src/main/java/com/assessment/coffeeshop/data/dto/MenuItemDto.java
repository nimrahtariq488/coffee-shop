package com.assessment.coffeeshop.data.dto;

import com.assessment.coffeeshop.data.entity.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MenuItemDto {

    private Integer quantity;
    private MenuItem menuItem;

}
