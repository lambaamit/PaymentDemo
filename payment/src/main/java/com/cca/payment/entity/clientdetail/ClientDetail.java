package com.cca.payment.entity.clientdetail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Builder
@Entity
@Table(name = "oauth_client", uniqueConstraints = {@UniqueConstraint(columnNames = {"client_key"})}, indexes = {
        @Index(name = "idx_client_key", columnList = "client_key")
})
@JsonIgnoreProperties(value = {"updateTimestamp"}, allowGetters = true, allowSetters = true)
@NoArgsConstructor
@AllArgsConstructor
public class ClientDetail implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -175409472330582262L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @Column(name = "client_key", nullable = false, updatable = false)
    private String clientKey;

    @Column(name = "client_secret", nullable = false, updatable = false)
    private String clientSecret;

    @Column(name = "scopes", nullable = false)
    private String scope;

    @Column(name = "authorized_grant_types", nullable = false)
    private String authorizedGrantTypes;

    @Column(name = "access_token_validity", nullable = true)
    private Long accessTokenValidityInSeconds;

    @Column(name = "refresh_token_validity", nullable = true)
    private Long refreshTokenValidityInSeconds;

    @Column(name = "created_date", nullable = true)
    private String createdDate;

    @UpdateTimestamp
    @Column(name = "update_timestamp", nullable = false, updatable = false)
    Timestamp updateTimestamp;
}