package com.rapmit.orderservice.service;

import com.rapmit.orderservice.dto.OrderRequest;

public interface OrderService {
    void placeOrder(OrderRequest orderRequest);
}
