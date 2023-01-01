package com.rapmit.productservice.service;

import com.rapmit.productservice.dto.ProductRequest;
import com.rapmit.productservice.dto.ProductResponse;
import com.rapmit.productservice.model.Product;
import com.rapmit.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public void createProduct(ProductRequest productRequest) {

        Product product=Product.builder()
                                .name(productRequest.getName())
                                .description(productRequest.getDescription())
                                .price(productRequest.getPrice())
                                .build();

        productRepository.save(product);
        log.info("product {} {} is saved",product.getId(),product.getName());

    }

    @Override
    public List<ProductResponse> getAllProducts() {

        List<Product> products = productRepository.findAll();

        List<ProductResponse> productResponses = products.stream().map(product -> mapToProductResponse(product)).collect(Collectors.toList());

        return productResponses;
    }

    private ProductResponse mapToProductResponse(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
