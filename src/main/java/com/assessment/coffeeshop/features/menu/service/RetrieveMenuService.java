package com.assessment.coffeeshop.features.menu.service;

import com.assessment.coffeeshop.data.dto.MenuInfo;
import com.assessment.coffeeshop.data.entity.Menu;
import com.assessment.coffeeshop.data.repository.MenuRepository;
import com.assessment.coffeeshop.features.menu.mapper.MenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class RetrieveMenuService {

    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    public RetrieveMenuService(MenuRepository menuRepository, MenuMapper menuMapper) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
    }

    public MenuInfo getMenu() {

        List<Menu> menuList = menuRepository.findAll();

        MenuInfo menuInfo = new MenuInfo();
        if (menuList.isEmpty()) {
            menuInfo.setMenuDetails(Collections.emptyList());
            return menuInfo;
        }

        menuInfo.setMenuDetails(menuMapper.map(menuList));

        return menuInfo;
    }
}
