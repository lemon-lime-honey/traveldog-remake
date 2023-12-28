package com.llh.traveldog.service.impl;

import java.util.Collections;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.llh.traveldog.config.security.JwtTokenPrivider;
import com.llh.traveldog.common.CommonResponse;
import com.llh.traveldog.data.dto.LoginResultDto;
import com.llh.traveldog.data.dto.SignUpResultDto;
import com.llh.traveldog.data.entity.Account;
import com.llh.traveldog.data.repository.AccountRepository;
import com.llh.traveldog.service.SignService;

@Service
public class SignServiceImpl implements SignService {
    private final Logger LOGGER = LoggerFactory.getLogger(SignServiceImpl.class);

    private AccountRepository accountRepository;
    private JwtTokenPrivider jwtTokenPrivider;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SignServiceImpl(AccountRepository accountRepository, JwtTokenPrivider jwtTokenPrivider, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.jwtTokenPrivider = jwtTokenPrivider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SignUpResultDto signUp(String username, String password, String name, String role) {
        Account account;
        if (role.equalsIgnoreCase("admin")) {
            account = Account.builder()
                .username(username)
                .name(name)
                .password(passwordEncoder.encode(password))
                .role(Collections.singletonList("ROLE_ADMIN"))
                .build();
        } else {
            account = Account.builder()
                .username(username)
                .name(name)
                .password(passwordEncoder.encode(password))
                .role(Collections.singletonList("ROLE_USER"))
                .build();
        }

        LOGGER.info(account.getPassword());

        Account savedAccount = accountRepository.save(account);
        SignUpResultDto signUpResultDto = new SignUpResultDto();

        LOGGER.info("[getSignUpResult] accountEntity 값이 들어왔는지 확인 후 결과값 주입");
        if (!savedAccount.getName().isEmpty()) {
            LOGGER.info("[getSignUpResult] 정상 처리 완료");
            setSuccessResult(signUpResultDto);
        } else {
            LOGGER.info("[getSignUpResult] 실패 처리 완료");
            setFailResult(signUpResultDto);
        }

        return signUpResultDto;
    }

    @Override
    public LoginResultDto login(String username, String password) throws RuntimeException, UsernameNotFoundException {
        LOGGER.info("[getLoginResult] signDataHandler로 회원 정보 요청");
        Optional<Account> account = accountRepository.findByUsername(username);

        if (!account.isPresent()) {
            LOGGER.error("[getLoginResult] 존재하지 않는 아이디");
            throw new UsernameNotFoundException("아이디가 존재하지 않습니다.");
        }

        LOGGER.info("[getLoginResult] id: {}", username);
        LOGGER.info("[getLoginResult] 비밀번호 비교 수행");

        Account foundAccount = account.get();
        if (!passwordEncoder.matches(password, foundAccount.getPassword())) {
            LOGGER.info("[getLoginResult] 비밀번호 불일치");
            throw new RuntimeException();
        }

        LOGGER.info("[getLoginResult] 비밀번호 일치");

        LOGGER.info("[getLoginResult] LoginResultDto 객체 생성");
        LoginResultDto loginResultDto = LoginResultDto.builder()
            .token(jwtTokenPrivider.createToken(String.valueOf(foundAccount.getUsername()), foundAccount.getRole()))
            .build();

        LOGGER.info("[getLoginResult] LoginResultDto 객체에 값 주입");
        setSuccessResult(loginResultDto);

        return loginResultDto;
    }

    private void setSuccessResult(SignUpResultDto result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    private void setFailResult(SignUpResultDto result) {
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
    }
}
