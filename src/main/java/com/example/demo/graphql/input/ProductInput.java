package com.example.demo.graphql.input;

import lombok.Data;

@Data
public class ProductInput {

    private Long id;
    private String name;
    private double price;

}