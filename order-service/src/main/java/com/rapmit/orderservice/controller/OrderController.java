package com.rapmit.orderservice.controller;

import com.rapmit.orderservice.dto.OrderRequest;
import com.rapmit.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
public class OrderController {


    @Autowired
    OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest)
    {
     orderService.placeOrder(orderRequest);

     return "Order placed Successfully";
    }
}
