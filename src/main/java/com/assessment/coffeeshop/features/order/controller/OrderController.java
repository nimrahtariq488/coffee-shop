package com.assessment.coffeeshop.features.order.controller;

import com.assessment.coffeeshop.constants.CommonConstants;
import com.assessment.coffeeshop.data.dto.OrderInfo;
import com.assessment.coffeeshop.data.dto.OrderRequest;
import com.assessment.coffeeshop.features.order.service.PlaceOrderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping(CommonConstants.ORDER)
public class OrderController {

    private final PlaceOrderService placeOrderService;

    /**
     * Class constructor to inject dependencies.
     */
    public OrderController(PlaceOrderService placeOrderService) {
        this.placeOrderService = placeOrderService;
    }

    @PostMapping
    public OrderInfo placeOrder(@Valid @RequestBody OrderRequest orderRequest) {
        return placeOrderService.placeOrder(orderRequest);
    }


}
