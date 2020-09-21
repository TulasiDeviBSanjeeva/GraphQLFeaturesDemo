package com.example.demo.graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseSummary {
    private Long purchaseId;
    private String customer;
    private String product;
    private int quantity;
}