/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.ChangePasswordRequestDto;
import com.ai.ecommercej4j.model.LoginRequestDto;
import com.ai.ecommercej4j.model.LoginResponseDto;
import com.ai.ecommercej4j.model.RegistrazioneRequestDto;
import com.ai.ecommercej4j.model.RegistrazioneResponseDto;
import com.ai.ecommercej4j.model.SpedizioneSearchDto;
import com.ai.ecommercej4j.model.SpedizioneSearchResultsDto;
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

    @Override
    public SpedizioneSearchResultsDto searchSpedizione(SpedizioneSearchDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
