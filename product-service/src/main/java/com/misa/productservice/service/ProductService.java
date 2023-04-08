package com.misa.productservice.service;

import com.misa.productservice.dto.ProductRequest;
import com.misa.productservice.dto.ProductResponse;
import com.misa.productservice.model.Product;
import com.misa.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductService {

    private final ProductRepository repo;


    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder().
                name(productRequest.getName()).
                description(productRequest.getDescription()).
                price(productRequest.getPrice()).build();
        repo.save(product);

        log.info(String.format("Product %s created.", product.getId()));
    }

    public List<ProductResponse> getAll() {
        return repo.findAll().stream()
                .map(this::mapProductToResponse)
                .collect(Collectors.toList());
    }

    private ProductResponse mapProductToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
