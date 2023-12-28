package com.llh.traveldog.service;

import com.llh.traveldog.data.dto.LoginResultDto;
import com.llh.traveldog.data.dto.SignUpResultDto;

public interface SignService {
    SignUpResultDto signUp(String username, String password, String name, String role);

    LoginResultDto login(String username, String password) throws RuntimeException;
}
