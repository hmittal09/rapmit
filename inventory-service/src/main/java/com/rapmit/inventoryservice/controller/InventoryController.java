package com.rapmit.inventoryservice.controller;

import com.rapmit.inventoryservice.dto.InventoryResponse;
import com.rapmit.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    //@PathVariable(name ="productCode") String productCode
    //@GetMapping("{productCode}")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> productCodes)
    {
           return inventoryService.isInStock(productCodes);
    }
}
