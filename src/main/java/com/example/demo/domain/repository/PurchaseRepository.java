package com.example.demo.domain.repository;

import com.example.demo.domain.entities.Purchase;
import com.example.demo.graphql.dto.PurchaseSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query("select p from Purchase p where p.customer.id = :customerId")
    List<Purchase> findAllByCustomerId(@Param("customerId") Long customerId);

    @Query("select new com.example.demo.graphql.dto.PurchaseSummary (o.id, cust.name,p.name, o.quantity) " +
            "from Purchase o inner join o.customer cust inner join o.product p")
    List<PurchaseSummary> retrievePurchaseSummary();

}
