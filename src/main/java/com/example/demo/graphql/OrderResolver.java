package com.example.demo.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.demo.domain.Customer;
import com.example.demo.domain.Order;
import com.example.demo.domain.Product;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderResolver implements GraphQLResolver<Order> {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    public String status(Order order) {
        return order.getStatus();
    }

    public Customer customer(Order order) {
        return customerService.findById(order.getCustomer().getId());
    }

    public Product product(Order order) {
        return productService.findById(order.getProduct().getId());
    }
}
