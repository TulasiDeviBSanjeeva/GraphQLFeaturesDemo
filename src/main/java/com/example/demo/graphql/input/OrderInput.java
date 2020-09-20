package com.example.demo.graphql.input;

import lombok.Data;

@Data
public class OrderInput {

    private Long id;
    private Integer quantity;
    private String status;
    private Long customerId;
    private Long productId;

}
