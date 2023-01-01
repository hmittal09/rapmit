package com.rapmit.productservice.service;

import com.rapmit.productservice.dto.ProductRequest;
import com.rapmit.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    void createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();
}
