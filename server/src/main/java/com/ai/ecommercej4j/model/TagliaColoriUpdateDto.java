/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.model;

import java.util.List;

/**
 *
 * @author utente
 */
public class TagliaColoriUpdateDto extends TagliaColoriRequestDto {
    private List <Colori> coloriSelezionati;

    public TagliaColoriUpdateDto(List<Colori> coloreSelezionato, Taglia taglia, Prodotto prodotto, String token) {
        super(taglia, prodotto, token);
        this.coloriSelezionati = coloreSelezionato;
    }

    public List <Colori> getColoriSelezionati() {
        return coloriSelezionati;
    }

    public void setColoriSelezionati(List <Colori> coloriSelezionati) {
        this.coloriSelezionati = coloriSelezionati;
    }
    
    
}
