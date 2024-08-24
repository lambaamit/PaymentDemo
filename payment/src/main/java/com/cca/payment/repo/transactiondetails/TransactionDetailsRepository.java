package com.cca.payment.repo.transactiondetails;



import com.cca.payment.dto.transactiondto.TransactionRecordResponse;
import com.cca.payment.entity.transactiondetails.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Integer> {


    List<TransactionRecordResponse> findByMerchantId(String merchantId);
}
