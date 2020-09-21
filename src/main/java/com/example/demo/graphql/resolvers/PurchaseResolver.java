package com.example.demo.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.demo.domain.entities.Customer;
import com.example.demo.domain.entities.Purchase;
import com.example.demo.domain.entities.Product;
import com.example.demo.domain.service.CustomerService;
import com.example.demo.domain.service.ProductService;

public class PurchaseResolver implements GraphQLResolver<Purchase> {

    private CustomerService customerService;
    private ProductService productService;

    public PurchaseResolver(CustomerService customerService, ProductService productService) {
      this.customerService = customerService;
      this.productService = productService;
    }

    public String status(Purchase purchase) {
        return purchase.getStatus();
    }

    public Customer customer(Purchase purchase) {
        return customerService.findById(purchase.getCustomer().getId());
    }

    public Product product(Purchase purchase) {
        return productService.findById(purchase.getProduct().getId());
    }
}
