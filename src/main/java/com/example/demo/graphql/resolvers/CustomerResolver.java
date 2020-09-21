package com.example.demo.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.demo.domain.entities.Customer;
import com.example.demo.domain.entities.Purchase;
import com.example.demo.domain.service.PurchaseService;

import java.util.List;

public class CustomerResolver implements GraphQLResolver<Customer> {

    private PurchaseService purchaseService;

    public CustomerResolver(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    public List<Purchase> customerPurchases(Customer customer) {
        return purchaseService.findAllByCustomer(customer);
    }

}
