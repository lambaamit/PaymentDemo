package com.cca.payment.repo.oauth;

import com.cca.payment.entity.oauth.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthAccessTokenRepository extends JpaRepository<AccessToken, Long> {

    AccessToken findByUsername(String username);
}