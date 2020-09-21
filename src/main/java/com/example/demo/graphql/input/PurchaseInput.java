package com.example.demo.graphql.input;

import lombok.Data;

@Data
public class PurchaseInput {

    private Long id;
    private Integer quantity;
    private String status;
    private Long customerId;
    private Long productId;

}
