package com.cca.payment.repo.userauth;

import com.cca.payment.entity.userauth.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Integer> {
    UserAuth findByUsername(String username);

    boolean existsByUsername(String username);
}
