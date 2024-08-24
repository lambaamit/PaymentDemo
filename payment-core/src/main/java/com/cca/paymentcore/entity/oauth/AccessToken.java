package com.cca.paymentcore.entity.oauth;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@Builder
@Table(name = "oauth_access_token", indexes = {
        @Index(name = "idx_client_id", columnList = "clientId"),
        @Index(name = "idx_username", columnList = "username")
})
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccessToken implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8213262289302879517L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oauthAccessTokenId;

    @Column(name = "token", columnDefinition = "TEXT")
    // @Transient
    private String token;

    @Column(name = "authentication_id")
    private String authenticationId;

    @Column(name = "username")
    private String username;

    @Column(name = "clientId")
    private String clientId;


    @Column(name = "refreshtoken", columnDefinition = "TEXT")
    private String refreshToken;


}