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
public class ProdottoUpdateDto extends ProdottoCreateDto {

    public ProdottoUpdateDto(Prodotto dati, String token) {
        super(dati, token);
    }

    public ProdottoUpdateDto() {
    }

}
