package com.example.demo.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo.domain.entities.Customer;
import com.example.demo.graphql.input.CustomerInput;
import com.example.demo.domain.service.CustomerService;
import org.modelmapper.ModelMapper;

import java.util.List;

public class CustomerGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    private CustomerService customerService;

    public CustomerGraphQL(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Customer customerById(Long id) {
        return customerService.findById(id);
    }

    public List<Customer> customers() {
        return customerService.findAll();
    }

    public Customer saveCustomer(CustomerInput input) {
        ModelMapper m = new ModelMapper();
        Customer c = m.map(input,Customer.class);
        return customerService.save(c);
    }

    public Boolean deleteCustomer(Long id) {
        return customerService.deleteById(id);
    }

}
