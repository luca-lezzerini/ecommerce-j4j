package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.*;
import com.ai.ecommercej4j.service.SecurityService;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements SecurityService{
   public String generateRandomString() {
       double d = Math.random();
       String str= (Double.toString(d));
       return str;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void checkDoubleOptin(LoginResponseDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoginResponseDto passwordDimenticata(LoginRequestDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reimpostaPassword(ChangePasswordRequestDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RegistrazioneResponseDto registrami(RegistrazioneRequestDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
