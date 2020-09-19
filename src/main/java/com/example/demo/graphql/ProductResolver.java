package com.example.demo.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.demo.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductResolver implements GraphQLResolver<Product> {

    public String realPrice(Product product) {
        return "Euros € : " + product.getPrice();
    }

}
