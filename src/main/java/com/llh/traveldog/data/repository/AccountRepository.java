package com.llh.traveldog.data.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.llh.traveldog.data.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
}
