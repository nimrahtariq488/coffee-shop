package com.assessment.coffeeshop.features.menu.controller;

import com.assessment.coffeeshop.constants.CommonConstants;
import com.assessment.coffeeshop.data.dto.MenuInfo;
import com.assessment.coffeeshop.features.menu.service.RetrieveMenuService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping(CommonConstants.MENU)
public class MenuController {

    private final RetrieveMenuService retrieveMenuService;

    public MenuController(RetrieveMenuService retrieveMenuService) {
        this.retrieveMenuService = retrieveMenuService;
    }

    @GetMapping
    public MenuInfo getMenu() {
        return retrieveMenuService.getMenu();
    }


}
