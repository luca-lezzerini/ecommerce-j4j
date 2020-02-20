/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.model;

import java.time.LocalDate;

/**
 *
 * @author utente
 */
public class OrdineSearchDto extends LoginResponseDto{
    private LocalDate searchData;
    private String searchNumeroOrdine;
    private String stato;

    public OrdineSearchDto() {
    }

    public OrdineSearchDto(LocalDate searchData, String searchNumeroOrdine, String stato) {
        this.searchData = searchData;
        this.searchNumeroOrdine = searchNumeroOrdine;
        this.stato = stato;
    }

    public LocalDate getSearchData() {
        return searchData;
    }

    public void setSearchData(LocalDate searchData) {
        this.searchData = searchData;
    }

    public String getSearchNumeroOrdine() {
        return searchNumeroOrdine;
    }

    public void setSearchNumeroOrdine(String searchNumeroOrdine) {
        this.searchNumeroOrdine = searchNumeroOrdine;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
    
    
    
    
    
}
