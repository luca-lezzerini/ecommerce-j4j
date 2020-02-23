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
    private int numeroPagina;
    private boolean ultimaPagina;

    public ProdottoSearchResultsDto(List<Prodotto> result, int numeroPagina, boolean ultimaPagina) {
        this.result = result;
        this.numeroPagina = numeroPagina;
        this.ultimaPagina = ultimaPagina;
    }  

    public ProdottoSearchResultsDto() {
    }

    public List<Prodotto> getResult() {
        return result;
    }

    public void setResult(List<Prodotto> result) {
        this.result = result;
    }

    public int getNumeroPagina() {
        return numeroPagina;
    }

    public void setNumeroPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    public boolean isUltimaPagina() {
        return ultimaPagina;
    }

    public void setUltimaPagina(boolean ultimaPagina) {
        this.ultimaPagina = ultimaPagina;
    }
}
