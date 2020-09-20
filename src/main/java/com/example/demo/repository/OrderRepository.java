package com.example.demo.repository;

import com.example.demo.domain.Order;
import com.example.demo.graphql.dto.OrderSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.customer.id = :customerId")
    List<Order> findAllByCustomerId(@Param("customerId") Long customerId);

    @Query("select new com.example.demo.graphql.dto.OrderSummary(o.id, cust.name,p.name, o.quantity) from Order o inner join o.customer cust inner join o.product p")
    List<OrderSummary> findAllOrderSummary();

}
