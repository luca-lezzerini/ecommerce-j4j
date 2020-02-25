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
public class TagliaColoriUpdateDto extends TagliaColoriRequestDto {
    private Colori coloreSelezionato;

    public TagliaColoriUpdateDto(Colori coloreSelezionato, Taglia taglia, Prodotto prodotto, String token) {
        super(taglia, prodotto, token);
        this.coloreSelezionato = coloreSelezionato;
    }

    public TagliaColoriUpdateDto() {
    }

    public Colori getColoreSelezionato() {
        return coloreSelezionato;
    }

    public void setColoreSelezionato(Colori coloreSelezionato) {
        this.coloreSelezionato = coloreSelezionato;
    }
    
    
    
}
