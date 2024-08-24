package com.cca.payment.entity.transactiondetails;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Builder
@Table(name = "TRANSACTION_DETAILS")
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetails {

    /**
     *
     */
    private static final long serialVersionUID = -3313399359525775574L;
    /**
     * This Field Define ID For TransactionDetails
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20)
    private String merchantId;

    @Column(name = "transaction_id", length = 20)
    private String transactionId;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "transaction_amount", length = 50)
    private double transactionAmount = 0.0d;


    @Column(name = "transaction_status", length = 50)
    private String transactionStatus;


    @PrePersist
    public void prePersist() {
        if (this.transactionId == null) {
            this.transactionId = UUID.randomUUID().toString().replace("-", "").substring(0, 7).toUpperCase();
        }
    }

}
