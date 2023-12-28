package com.llh.traveldog.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.llh.traveldog.data.dto.LoginResultDto;
import com.llh.traveldog.data.dto.SignUpResultDto;
import com.llh.traveldog.service.SignService;

@RestController
@RequestMapping("/sign-api")
public class SignController {
    private final Logger LOGGER = LoggerFactory.getLogger(SignController.class);
    private final SignService signService;

    @Autowired
    public SignController(SignService signService) {
        this.signService = signService;
    }

    @PostMapping("/login")
    public LoginResultDto login(@RequestParam String username, @RequestParam String password) throws RuntimeException {
        LOGGER.info("[login] 로그인을 시도하고 있습니다. id: {}", username);
        LoginResultDto loginResultDto = signService.login(username, password);

        if (loginResultDto.getCode() == 0) {
            LOGGER.info("[login] 정상적으로 로그인되었습니다. id: {}, token: {}", username, loginResultDto.getToken());
        }
        return loginResultDto;
    }

    @PostMapping("/sign-up")
    public SignUpResultDto signUp(@RequestParam String username, @RequestParam String password, @RequestParam String name, @RequestParam String role) {
        LOGGER.info("[signUp] 회원가입을 수행합니다. id: {}, name: {}, role: {}", username, name, role);
        SignUpResultDto signUpResultDto = signService.signUp(username, password, name, role);

        LOGGER.info("[signUp] 회원가입을 완료했습니다. id: {}", username);
        return signUpResultDto;
    }

    @GetMapping("/exception")
    public void exceptionTest() throws RuntimeException {
        throw new RuntimeException("접근이 금지되었습니다.");
    }

    @ExceptionHandler(RuntimeException.class) 
    public ResponseEntity<Map<String, String>> ExceptionHandler(RuntimeException e) {
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        LOGGER.error("ExeptionHandler 호출, {}, {}", e.getCause(), e.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "오류 발생");

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }
}
