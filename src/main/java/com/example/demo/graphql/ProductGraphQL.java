package com.example.demo.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo.domain.Product;
import com.example.demo.graphql.input.ProductInput;
import com.example.demo.service.ProductService;
import org.modelmapper.ModelMapper;

import java.util.List;

public class ProductGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    private ProductService productService;

    public ProductGraphQL(ProductService productService) {
        this.productService = productService;
    }

    public Product product(Long id) {
        return productService.findById(id);
    }

    public List<Product> products() {
        return productService.findAll();
    }

    public Product saveProduct(ProductInput input) {
        ModelMapper m = new ModelMapper();
        Product product = m.map(input,Product.class);
        return productService.save(product);
    }

    public Boolean deleteProduct(Long id) {
        return productService.deleteById(id);
    }

}
