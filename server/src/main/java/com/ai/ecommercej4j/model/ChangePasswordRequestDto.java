/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.model;

/**
 *
 * @author utente
 */
public class ChangePasswordRequestDto {
    private String doi;
    private String password;

    public ChangePasswordRequestDto(String doi, String password) {
        this.doi = doi;
        this.password = password;
    }

    public ChangePasswordRequestDto() {
    }
    
    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
