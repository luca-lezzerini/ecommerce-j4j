/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.model;

public class LoginResponseDto {

    public LoginResponseDto() {
    }
    
    //token in SecurityServiceImpl viene considerato come doi
    public LoginResponseDto(String token) {
        this.token = token;
    }
    
    
   private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
   
    

}
