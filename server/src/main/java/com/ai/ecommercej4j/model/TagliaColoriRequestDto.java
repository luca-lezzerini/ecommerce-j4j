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
public class TagliaColoriRequestDto extends LoginResponseDto {
    private Taglia taglia;
    private Prodotto prodotto;

    public TagliaColoriRequestDto(Taglia taglia, Prodotto prodotto, String token) {
        super(token);
        this.taglia = taglia;
        this.prodotto = prodotto;
    }

    public TagliaColoriRequestDto() {
    }

    public Taglia getTaglia() {
        return taglia;
    }

    public void setTaglia(Taglia taglia) {
        this.taglia = taglia;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }
    
    

    
    
}
