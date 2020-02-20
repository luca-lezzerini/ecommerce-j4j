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
public class OrdineSearchDto extends LoginResponseDto{
    private String searchData;
    private String searchNumeroOrdine;
    private String stato;

    public OrdineSearchDto() {
    }

    public OrdineSearchDto(String searchData, String searchNumeroOrdine, String stato) {
        this.searchData = searchData;
        this.searchNumeroOrdine = searchNumeroOrdine;
        this.stato = stato;
    }

    public String getSearchData() {
        return searchData;
    }

    public void setSearchData(String searchData) {
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
