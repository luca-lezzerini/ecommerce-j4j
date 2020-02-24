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
public class OrdineSearchResultsDto extends LoginResponseDto {

    private int page;
    private List<Ordine> results;
    private boolean ultimaPagina;

    public OrdineSearchResultsDto(List<Ordine> results, int page, boolean ultimaPagina) {
        this.results = results;
        this.page = page;
        this.ultimaPagina = ultimaPagina;
    }

    public OrdineSearchResultsDto() {
    }

    public List<Ordine> getResults() {
        return results;
    }

    public void setResults(List<Ordine> results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public boolean isUltimaPagina() {
        return ultimaPagina;
    }

    public void setUltimaPagina(boolean ultimaPagina) {
        this.ultimaPagina = ultimaPagina;
    }

}
