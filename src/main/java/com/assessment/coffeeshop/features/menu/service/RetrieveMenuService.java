package com.assessment.coffeeshop.features.menu.service;

import com.assessment.coffeeshop.data.dto.MenuInfo;
import com.assessment.coffeeshop.data.entity.Menu;
import com.assessment.coffeeshop.data.repository.MenuRepository;
import com.assessment.coffeeshop.exception.CoffeeShopException;
import com.assessment.coffeeshop.features.menu.mapper.MenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RetrieveMenuService {

    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    /**
     * Class constructor to inject dependencies.
     */
    public RetrieveMenuService(MenuRepository menuRepository, MenuMapper menuMapper) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
    }

    /**
     * Get menu.
     *
     * @return menuInfo
     */
    public MenuInfo getMenu() {

        List<Menu> menuList = menuRepository.findAll();

        if (menuList.isEmpty()) {
            log.error("Menu list not found!");
            throw new CoffeeShopException("404", "Menu not found!");
        }

        MenuInfo menuInfo = new MenuInfo();
        menuInfo.setMenuDetails(menuMapper.map(menuList));
        return menuInfo;
    }
}
