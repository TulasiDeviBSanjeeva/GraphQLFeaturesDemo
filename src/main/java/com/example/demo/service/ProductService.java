package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Transactional
    public Product save(Product p) {
        return productRepository.save(p);
    }

    @Transactional
    public Boolean deleteById(Long id) {
        if(productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
