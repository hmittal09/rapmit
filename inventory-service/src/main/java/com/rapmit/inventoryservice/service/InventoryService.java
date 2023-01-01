package com.rapmit.inventoryservice.service;

import com.rapmit.inventoryservice.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {
    List<InventoryResponse> isInStock(List<String> productCodes);
}
