package com.example.demo.service;

import com.example.demo.domain.Customer;
import com.example.demo.domain.Order;
import com.example.demo.graphql.dto.OrderSummary;
import com.example.demo.graphql.exceptions.DomainException;
import com.example.demo.repository.OrderRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable).getContent();
    }

    @SneakyThrows
    @Transactional
    public Order save(Order order) {
        if(order.getQuantity() > 100) {
            throw new DomainException("It is not possible to make a purchase with more than 100 items");
        }
        return orderRepository.save(order);
    }

    @Transactional
    public boolean deleteById(Long id) {
        if(orderRepository.findById(id).isPresent()) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Order> findAllByCustomer(Customer customer) {
        return orderRepository.findAllByCustomerId(customer.getId());
    }

    public List<OrderSummary> findAllOrderSummary() {
        return orderRepository.findAllOrderSummary();
    }

}
