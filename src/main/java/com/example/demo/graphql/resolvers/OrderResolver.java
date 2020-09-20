package com.example.demo.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.demo.domain.Customer;
import com.example.demo.domain.Order;
import com.example.demo.domain.Product;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ProductService;

public class OrderResolver implements GraphQLResolver<Order> {

    private CustomerService customerService;
    private ProductService productService;

    public OrderResolver(CustomerService customerService, ProductService productService) {
      this.customerService = customerService;
      this.productService = productService;
    }

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
