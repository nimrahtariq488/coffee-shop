package com.assessment.coffeeshop.features.menu.mapper;

import com.assessment.coffeeshop.data.dto.MenuDetail;
import com.assessment.coffeeshop.data.dto.MenuItem;
import com.assessment.coffeeshop.data.entity.Menu;
import com.assessment.coffeeshop.data.entity.MenuItemCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    @Mapping(target = "menuName", source = "menu.name")
    MenuDetail mapMenu(Menu menu);

    List<MenuDetail> map(List<Menu> menu);

    @Mapping(target = "menuItemName", source = "menuItem.name")
    @Mapping(target = "category", source = "menuItem.category", qualifiedByName = "mapCategory")
    MenuItem mapMenuItem(com.assessment.coffeeshop.data.entity.MenuItem menuItem);

    List<MenuItem> mapMenuItems(List<com.assessment.coffeeshop.data.entity.MenuItem> menuItems);

    /**
     * Maps menu item category to category enum.
     */
    @Named("mapCategory")
    default MenuItem.CategoryEnum mapCategory(MenuItemCategory category) {
        return !ObjectUtils.isEmpty(category)
                && !ObjectUtils.isEmpty(category.getCategoryId())
                ? MenuItem.CategoryEnum.fromValue(category.getCategoryId())
                : null;
    }

}
