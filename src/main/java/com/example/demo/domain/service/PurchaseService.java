package com.example.demo.domain.service;

import com.example.demo.domain.entities.Customer;
import com.example.demo.domain.entities.Purchase;
import com.example.demo.graphql.dto.PurchaseSummary;
import com.example.demo.graphql.exceptions.DomainException;
import com.example.demo.domain.repository.PurchaseRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public Purchase findById(Long id) {
        return purchaseRepository.findById(id).orElse(null);
    }

    public List<Purchase> findAll(Pageable pageable) {
        return purchaseRepository.findAll(pageable).getContent();
    }

    @SneakyThrows
    @Transactional
    public Purchase save(Purchase purchase) {
        if(purchase.getQuantity() > 100) {
            throw new DomainException("It is not possible to make a purchase with more than 100 items");
        }
        return purchaseRepository.save(purchase);
    }

    @Transactional
    public boolean delete(Long id) {
        if(purchaseRepository.findById(id).isPresent()) {
            purchaseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Purchase> findAllByCustomer(Customer customer) {
        return purchaseRepository.findAllByCustomerId(customer.getId());
    }

    public List<PurchaseSummary> retrievePurchaseSummary() {
        return purchaseRepository.retrievePurchaseSummary();
    }

}
