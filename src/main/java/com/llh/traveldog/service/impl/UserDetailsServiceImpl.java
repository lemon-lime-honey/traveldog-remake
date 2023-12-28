package com.llh.traveldog.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;
import com.llh.traveldog.data.entity.Account;
import com.llh.traveldog.data.repository.AccountRepository;
import lombok.RequiredArgsConstructor;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final AccountRepository accountRepository;

    public UserDetailsServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByUsername(username);
        UserBuilder builder = null;
        if (account.isPresent()) {
            Account currentAccount = account.get();
            builder = User.withUsername(username);
            builder.password(currentAccount.getPassword());
            builder.authorities(currentAccount.getAuthorities());
        } else {
            LOGGER.error("[loadUserByUsername] loadUserByUsername 실패. username: {}", username);
            throw new UsernameNotFoundException("User not found.");
        }
        LOGGER.info("[loadUserByUsername] loadUserByUsername 수행. username: {}", username);
        return builder.build();
    }
}
