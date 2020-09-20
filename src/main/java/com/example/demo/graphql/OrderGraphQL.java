package com.example.demo.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo.domain.Order;
import com.example.demo.graphql.dto.OrderSummary;
import com.example.demo.graphql.input.OrderInput;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;

public class OrderGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    private OrderService orderService;
    private CustomerService customerService;
    private ProductService productService;

    public OrderGraphQL(CustomerService customerService, ProductService productService, OrderService orderService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.productService = productService;
    }

    public Order order(Long id) {
        return orderService.findById(id);
    }

    public List<Order> orders(int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("quantity"));
        return orderService.findAll(pageable);
    }

    public List<OrderSummary> orderSummary() {
        return orderService.findAllOrderSummary();
    }

    public Order saveOrder(OrderInput input) {
        ModelMapper m = new ModelMapper();
        Order order = m.map(input, Order.class);

        order.setDate(new Date());
        order.setCustomer(customerService.findById(input.getCustomerId()));
        order.setProduct(productService.findById(input.getProductId()));
        return orderService.save(order);
    }

    public Boolean deleteOrder(Long id) {
        return orderService.deleteById(id);
    }

}

