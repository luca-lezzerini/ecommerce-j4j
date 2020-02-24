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
public class OrdineSearchDto extends LoginResponseDto {

    private LocalDate searchData;
    private Integer searchNumeroOrdine;
    private String stato;
    private int page;

    public OrdineSearchDto() {
    }

    public OrdineSearchDto(LocalDate searchData, Integer searchNumeroOrdine, String stato, int page) {
        this.searchData = searchData;
        this.searchNumeroOrdine = searchNumeroOrdine;
        this.stato = stato;
        this.page = page;
    }

    public LocalDate getSearchData() {
        return searchData;
    }

    public void setSearchData(LocalDate searchData) {
        this.searchData = searchData;
    }

    public Integer getSearchNumeroOrdine() {
        return searchNumeroOrdine;
    }

    public void setSearchNumeroOrdine(Integer searchNumeroOrdine) {
        this.searchNumeroOrdine = searchNumeroOrdine;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

}
