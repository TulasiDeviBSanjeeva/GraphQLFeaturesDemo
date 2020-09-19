package com.example.demo.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo.domain.Order;
import com.example.demo.graphql.dto.OrderSummary;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class OrderGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private OrderService service;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    public Order order(Long id) {
        return service.findById(id);
    }

    public List<Order> orders(int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("quantity"));
        return service.findAll(pageable);
    }

    public List<OrderSummary> orderSummary() {
        return service.findAllOrderSummary();
    }

    public Order saveOrder(OrderInput input) {
        ModelMapper m = new ModelMapper();
        Order order = m.map(input, Order.class);

        order.setDate(new Date());
        order.setCustomer(customerService.findById(input.getCustomerId()));
        order.setProduct(productService.findById(input.getProductId()));
        return service.save(order);
    }

    public Boolean deleteOrder(Long id) {
        return service.deleteById(id);
    }

}

