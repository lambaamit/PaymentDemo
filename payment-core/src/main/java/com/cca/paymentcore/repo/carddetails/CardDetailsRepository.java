package com.cca.paymentcore.repo.carddetails;


import com.cca.paymentcore.entity.carddetails.CardDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardDetailsRepository extends JpaRepository<CardDetails, Integer> {

    CardDetails findByCardNumberAndMerchantId(String cardNumber, String merchantId);

}
