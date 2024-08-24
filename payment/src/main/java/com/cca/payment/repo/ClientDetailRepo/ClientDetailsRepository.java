package com.cca.payment.repo.ClientDetailRepo;


import com.cca.payment.entity.clientdetail.ClientDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDetailsRepository extends JpaRepository<ClientDetail, Long> {

    ClientDetail findByClientId(Long clientId);

    ClientDetail findByClientKey(String clientKey);

    ClientDetail findByclientKeyAndClientSecret(String clientKey, String clientSecret);

}
