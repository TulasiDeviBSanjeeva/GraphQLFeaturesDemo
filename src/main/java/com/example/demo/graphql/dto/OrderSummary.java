package com.example.demo.graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderSummary {
    private Long orderId;
    private String customer;
    private String product;
    private int quantity;
}