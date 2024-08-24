package com.cca.payment.entity.userauth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@Builder
@Table(name = "USER_AUTH")
@NoArgsConstructor
@AllArgsConstructor
public class UserAuth {

    /**
     *
     */
    private static final long serialVersionUID = -3313399359525775574L;
    /**
     * This Field Define ID For RefreshToken
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20)
    private String channel;


    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "merchant_id", length = 100)
    private String merchantId;

    @Column(name = "device_id", length = 50)
    private String deviceIid;
    @Column(name = "device_type", length = 50)
    private String deviceType;

    @Column(name = "is_active")
    private boolean isActive;


}
