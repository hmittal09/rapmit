package com.rapmit.inventoryservice.service;

import com.rapmit.inventoryservice.dto.InventoryResponse;
import com.rapmit.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService{

    @Autowired
    InventoryRepository inventoryRepository;
    @Override
    public List<InventoryResponse> isInStock(List<String> productCodes) {

        return inventoryRepository.findByProductCodeIn(productCodes)
                .stream()
                .map(inventory -> {
                    return InventoryResponse.builder()
                            .productCode(inventory.getProductCode())
                            .isInstock(inventory.getQuantity()>0)
                            .build();
                }).collect(Collectors.toList());
    }
}
