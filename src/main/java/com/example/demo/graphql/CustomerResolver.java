package com.example.demo.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.demo.domain.Customer;
import com.example.demo.domain.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerResolver implements GraphQLResolver<Customer> {

    @Autowired
    private OrderService orderService;

    public List<Order> orders(Customer c) {
        return orderService.findAllByCustomer(c);
    }
}
