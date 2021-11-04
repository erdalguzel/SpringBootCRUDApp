package com.example.demo.implementation;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> productDb = this.productRepository.findById(product.getId());
        if (productDb.isPresent()) {
            Product productUpdate = productDb.get();
            productUpdate.setId(product.getId());
            productUpdate.setPrice(product.getPrice());
            productUpdate.setName(product.getName());
            productUpdate.setDescription(product.getDescription());
            return productUpdate;
        } else {
            throw new ResourceNotFoundException("Resource not found with id " + product.getId());
        }
    }

    @Override
    public Product getProductById(long productId) {
        Optional<Product> productDB = this.productRepository.findById(productId);
        if(productDB.isPresent()) {
            return productDB.get();
        } else {
            throw new ResourceNotFoundException("Resource not found with id " + productId);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public void deleteProduct(long productId) {
        Optional<Product> productDb = productRepository.findById(productId);
        if(productDb.isPresent()) {
            this.productRepository.delete(productDb.get());
        } else {
            throw new ResourceNotFoundException("Resource not found with id " + productId);
        }
    }
}
