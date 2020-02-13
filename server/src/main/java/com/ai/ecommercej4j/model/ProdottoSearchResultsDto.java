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
public class ProdottoSearchResultsDto {

    private List<Prodotto> result;

    public ProdottoSearchResultsDto(List<Prodotto> results) {
        this.result = results;
    }

    public ProdottoSearchResultsDto() {
    }

    public List<Prodotto> getResults() {
        return result;
    }

    public void setResults(List<Prodotto> results) {
        this.result = results;
    }

}
