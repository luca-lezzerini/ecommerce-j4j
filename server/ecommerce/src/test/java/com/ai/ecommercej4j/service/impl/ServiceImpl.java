/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.LoginRequestDto;
import com.ai.ecommercej4j.service.SecurityService;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements SecurityService{
   public generateRandomString(String str) {
       return str;
    }

    @Override
    public LoginRequestDto login() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
