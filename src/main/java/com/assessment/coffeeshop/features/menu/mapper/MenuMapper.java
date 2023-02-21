package com.assessment.coffeeshop.features.menu.mapper;

import com.assessment.coffeeshop.data.dto.MenuDetail;
import com.assessment.coffeeshop.data.dto.MenuItem;
import com.assessment.coffeeshop.data.entity.Menu;
import com.assessment.coffeeshop.data.entity.MenuItemCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuMapper  {

    @Mapping(target = "menuName", source = "menu.name")
    public MenuDetail mapMenu(Menu menu);

    public List<MenuDetail> map(List<Menu> menu);


    @Mapping(target = "menuItemName", source = "menuItem.name")
    @Mapping(target = "category", source = "menuItem.category", qualifiedByName = "mapCategory")
    public MenuItem mapMenuItem(com.assessment.coffeeshop.data.entity.MenuItem menuItem);


    public List<MenuItem> mapMenuItems(List<com.assessment.coffeeshop.data.entity.MenuItem> menuItems);

    @Named("mapCategory")
    default MenuItem.CategoryEnum mapCategory(MenuItemCategory category) {
        return !ObjectUtils.isEmpty(category)
                && !ObjectUtils.isEmpty(category.getCategoryId())
                ? MenuItem.CategoryEnum.fromValue(category.getCategoryId())
                : null;
    }

}
