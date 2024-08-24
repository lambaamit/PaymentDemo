package com.cca.paymentcore.repo.oauth;

import com.cca.paymentcore.entity.oauth.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthAccessTokenRepository extends JpaRepository<AccessToken, Long> {

    AccessToken findByUsername(String username);
}