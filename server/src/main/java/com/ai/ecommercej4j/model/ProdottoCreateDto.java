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
public class ProdottoCreateDto extends LoginResponseDto {

    private Prodotto dati;

    public ProdottoCreateDto(Prodotto dati, String token) {
        super(token);
        this.dati = dati;
    }

    public ProdottoCreateDto() {
    }

    public Prodotto getDati() {
        return dati;
    }

    public void setDati(Prodotto dati) {
        this.dati = dati;
    }

}
