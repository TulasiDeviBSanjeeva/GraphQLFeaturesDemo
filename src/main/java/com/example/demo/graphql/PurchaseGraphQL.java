package com.example.demo.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo.domain.entities.Purchase;
import com.example.demo.graphql.dto.PurchaseSummary;
import com.example.demo.graphql.input.PurchaseInput;
import com.example.demo.domain.service.CustomerService;
import com.example.demo.domain.service.PurchaseService;
import com.example.demo.domain.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;

public class PurchaseGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    private PurchaseService purchaseService;
    private CustomerService customerService;
    private ProductService productService;

    public PurchaseGraphQL(CustomerService customerService, ProductService productService, PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
        this.customerService = customerService;
        this.productService = productService;
    }

    public Purchase purchaseById(Long id) {
        return purchaseService.findById(id);
    }

    public List<Purchase> purchases(int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("quantity"));
        return purchaseService.findAll(pageable);
    }

    public List<PurchaseSummary> purchaseSummary() {
        return purchaseService.retrievePurchaseSummary();
    }

    public Purchase savePurchase(PurchaseInput input) {
        ModelMapper m = new ModelMapper();
        Purchase purchase = m.map(input, Purchase.class);

        purchase.setDate(new Date());
        purchase.setCustomer(customerService.findById(input.getCustomerId()));
        purchase.setProduct(productService.findById(input.getProductId()));
        return purchaseService.save(purchase);
    }

    public Boolean deletePurchase(Long id) {
        return purchaseService.delete(id);
    }

}

