package com.example.demo.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo.domain.Customer;
import com.example.demo.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private CustomerService service;

    public Customer customer(Long id) {
        return service.findById(id);
    }

    public List<Customer> customers() {
        return service.findAll();
    }

    public Customer saveCustomer(CustomerInput input) {
        ModelMapper m = new ModelMapper();
        Customer c = m.map(input,Customer.class);
        return service.save(c);
    }

    public Boolean deleteCustomer(Long id) {
        return service.deleteById(id);
    }

}
