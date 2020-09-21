package com.example.demo;

import com.coxautodev.graphql.tools.SchemaParser;
import com.example.demo.graphql.CustomerGraphQL;
import com.example.demo.graphql.PurchaseGraphQL;
import com.example.demo.graphql.ProductGraphQL;
import com.example.demo.graphql.resolvers.CustomerResolver;
import com.example.demo.graphql.resolvers.PurchaseResolver;
import com.example.demo.graphql.resolvers.ProductResolver;
import com.example.demo.graphql.scalar.DateScalar;
import com.example.demo.domain.service.CustomerService;
import com.example.demo.domain.service.PurchaseService;
import com.example.demo.domain.service.ProductService;
import graphql.execution.AsyncExecutionStrategy;
import graphql.execution.ExecutionStrategy;
import graphql.schema.GraphQLScalarType;
import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLServlet;
import graphql.servlet.SimpleGraphQLServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication
public class GraphQLFeaturesDemoApplication {

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    @Autowired
    PurchaseService purchaseService;

    public static void main(String[] args) {
        SpringApplication.run(GraphQLFeaturesDemoApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {

        GraphQLSchema schema  = SchemaParser.newParser()
                .resolvers(customerResolver(purchaseService), customerGraphQL(customerService))
                .resolvers(productResolver(), productGraphQL(productService))
                .resolvers(orderResolver(customerService, productService),
                        orderGraphQL(customerService, productService, purchaseService))
                .scalars(dateScalarType())
                .file("graphql/customer.graphqls")
                .file("graphql/product.graphqls")
                .file("graphql/purchase.graphqls")
                .build().makeExecutableSchema();
        ExecutionStrategy executionStrategy = new AsyncExecutionStrategy();
        GraphQLServlet servlet = new SimpleGraphQLServlet(schema, executionStrategy);
        ServletRegistrationBean bean = new ServletRegistrationBean(servlet, "/graphql");
        return bean;

    }


    @Bean
    public GraphQLScalarType dateScalarType() {
        return new DateScalar();
    }


    @Bean
    public CustomerResolver customerResolver(PurchaseService purchaseService) {
        return new CustomerResolver(purchaseService);
    }

    @Bean
    public ProductResolver productResolver() {
        return new ProductResolver();
    }

    @Bean
    public PurchaseResolver orderResolver(CustomerService customerService, ProductService productService) {
        return new PurchaseResolver(customerService, productService);
    }

    @Bean
    public CustomerGraphQL customerGraphQL(CustomerService customerService) {
        return new CustomerGraphQL(customerService);
    }

    @Bean
    public ProductGraphQL productGraphQL(ProductService productService) {
        return new ProductGraphQL(productService);
    }

    @Bean
    public PurchaseGraphQL orderGraphQL(CustomerService customerService, ProductService productService, PurchaseService purchaseService) {
        return new PurchaseGraphQL(customerService, productService, purchaseService);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("*").allowedOrigins("http://localhost:*");
            }
        };
    }

}


