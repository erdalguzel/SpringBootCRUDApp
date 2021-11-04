package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    Product updateProduct(Product product);

    Product getProductById(long productId);

    List<Product> getAllProducts();

    void deleteProduct(long productId);
}
