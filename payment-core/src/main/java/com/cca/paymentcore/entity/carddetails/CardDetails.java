package com.cca.paymentcore.entity.carddetails;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Builder
@Table(name = "CARD_DETAILS")
@NoArgsConstructor
@AllArgsConstructor
public class CardDetails {

    /**
     *
     */
    private static final long serialVersionUID = -3313399359525775574L;
    /**
     * This Field Define ID For Card Details
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20)
    private String merchantId;

    @Column(name = "credit_card_number", length = 20)
    private String cardNumber;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "card_holder_name", length = 50)
    private String cardHolderName;

    @Column(name = "card_limit", length = 50)
    private double cardLimit = 0.0d;

    @Column(name = "available_card_limit", length = 50)
    private double availableCardLimit = 0.0d;

    @Column(name = "card_expiry", length = 100)
    private Date cardExpiry;

    @Column(name = "card_cvv", length = 50)
    private String cardCvv;

}
