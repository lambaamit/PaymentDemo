package com.cca.paymentcore.repo.transactiondetails;


import com.cca.paymentcore.entity.transactiondetails.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Integer> {


}
