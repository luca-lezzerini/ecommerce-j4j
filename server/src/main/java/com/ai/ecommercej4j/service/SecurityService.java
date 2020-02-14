package com.ai.ecommercej4j.service;

import com.ai.ecommercej4j.model.*;

public interface SecurityService {

    LoginResponseDto login(LoginRequestDto dto);

    void checkDoubleOptin(LoginResponseDto dto);

    LoginResponseDto passwordDimenticata(LoginRequestDto dto);
    
    RegistrazioneResponseDto registrami(RegistrazioneRequestDto dto);
    
    void reimpostaPassword(ChangePasswordRequestDto dto);
    
}
