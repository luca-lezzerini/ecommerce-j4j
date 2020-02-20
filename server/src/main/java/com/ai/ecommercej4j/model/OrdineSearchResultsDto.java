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
    private List<Ordine> results;

    public OrdineSearchResultsDto(List<Ordine> results) {
        this.results = results;
    }

    public OrdineSearchResultsDto() {
    }

    public List<Ordine> getResults() {
        return results;
    }

    public void setResults(List<Ordine> results) {
        this.results = results;
    }
    
    
}
