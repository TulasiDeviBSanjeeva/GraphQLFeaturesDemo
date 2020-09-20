package com.example.demo.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.demo.domain.Customer;
import com.example.demo.domain.Order;
import com.example.demo.service.OrderService;

import java.util.List;

public class CustomerResolver implements GraphQLResolver<Customer> {

    private final OrderService orderService;

    public CustomerResolver(OrderService orderService) {
        this.orderService = orderService;
    }

    public List<Order> orders(Customer c) {
        return orderService.findAllByCustomer(c);
    }
}
