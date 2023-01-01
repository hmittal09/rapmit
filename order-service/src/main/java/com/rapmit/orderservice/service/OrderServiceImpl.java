package com.rapmit.orderservice.service;

import com.rapmit.orderservice.dto.InventoryResponse;
import com.rapmit.orderservice.dto.OrderLineItemDto;
import com.rapmit.orderservice.dto.OrderRequest;
import com.rapmit.orderservice.model.OderLineItems;
import com.rapmit.orderservice.model.Order;
import com.rapmit.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private WebClient.Builder  webClient;
    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OderLineItems> orderLineItems = orderRequest.getOrderLineItemDtos().stream().map(orderLineItem -> mapTOOrder(orderLineItem)).collect(Collectors.toList());

        order.setOderLineItems(orderLineItems);

        //calling inventory service

        List<String> productCodeList = order.getOderLineItems().stream().map(OderLineItems::getProductCode).collect(Collectors.toList());

        InventoryResponse[] inventoryResponses = webClient.build()
                .get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("productCodes", productCodeList).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsAvailable = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::getIsInstock);

        if (allProductsAvailable) {
            orderRepository.save(order);
        } else
        {
            throw new IllegalArgumentException("product not in stock please try after some time");
        }

    }

    private OderLineItems mapTOOrder(OrderLineItemDto orderLineItem) {

        return OderLineItems.builder()
                .price(orderLineItem.getPrice())
                .productCode(orderLineItem.getProductCode())
                .quantity(orderLineItem.getQuantity())
                .price(orderLineItem.getPrice())
                .build();

    }
}
