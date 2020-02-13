package com.ai.ecommercej4j.service;

import com.ai.ecommercej4j.model.LoginRequestDto;
import com.ai.ecommercej4j.model.LoginResponseDto;

public interface SecurityService {

    public LoginResponseDto login(LoginRequestDto dto);

    public void checkDoubleOptin(LoginResponseDto dto);

    public LoginResponseDto passwordDimenticata(LoginRequestDto dto);
}
