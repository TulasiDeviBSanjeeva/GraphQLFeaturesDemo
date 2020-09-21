package com.example.demo.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.demo.domain.entities.Product;

public class ProductResolver implements GraphQLResolver<Product> {

    public String realPrice(Product product) {
        return "Euros € : " + product.getPrice();
    }

}
