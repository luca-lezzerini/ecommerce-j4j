package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.*;
import com.ai.ecommercej4j.repository.UtenteRepository;
import com.ai.ecommercej4j.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private UtenteRepository ur;

    private String generateRandomString() {
        double d = Math.random();
        String str = (Double.toString(d));
        return str;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void checkDoubleOptin(LoginResponseDto dto) {

    }

    @Override
    public LoginResponseDto passwordDimenticata(LoginRequestDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reimpostaPassword(ChangePasswordRequestDto dto) {

    }

    @Override
    public RegistrazioneResponseDto registrami(RegistrazioneRequestDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean checkToken(String tok) {
        return ur.findByToken(tok) != null;
    }

}
