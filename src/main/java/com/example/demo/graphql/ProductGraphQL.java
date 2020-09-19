package com.example.demo.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private ProductService service;

    public Product product(Long id) {
        return service.findById(id);
    }

    public List<Product> products() {
        return service.findAll();
    }

    public Product saveProduct(ProductInput input) {
        ModelMapper m = new ModelMapper();
        Product product = m.map(input,Product.class);
        return service.save(product);
    }

    public Boolean deleteProduct(Long id) {
        return service.deleteById(id);
    }

}
