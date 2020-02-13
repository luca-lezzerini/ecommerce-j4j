package com.ai.ecommercej4j.service;

import com.ai.ecommercej4j.model.*;

public interface SecurityService {

    public LoginResponseDto login(LoginRequestDto dto);

    public void checkDoubleOptin(LoginResponseDto dto);

    public LoginResponseDto passwordDimenticata(LoginRequestDto dto);
    
    public RegistrazioneResponseDto registrami(RegistrazioneRequestDto dto);
    
    public void reimpostaPassword(ChangePasswordRequestDto dto);
    
    public SpedizioneSearchResultsDto searchSpedizione (SpedizioneSearchDto dto);
    
}
