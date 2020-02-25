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
    private List <Colori> coloreSelezionato;

    public TagliaColoriUpdateDto(List<Colori> coloreSelezionato, Taglia taglia, Prodotto prodotto, String token) {
        super(taglia, prodotto, token);
        this.coloreSelezionato = coloreSelezionato;
    }

    public List <Colori> getColoreSelezionato() {
        return coloreSelezionato;
    }

    public void setColoreSelezionato(List <Colori> coloreSelezionato) {
        this.coloreSelezionato = coloreSelezionato;
    }
    
    
}
