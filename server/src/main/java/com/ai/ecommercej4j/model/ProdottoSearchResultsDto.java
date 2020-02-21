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
    private String operazione;

    public ProdottoSearchResultsDto(List<Prodotto> result, int numeroPagina, String operazione) {
        this.result = result;
        this.numeroPagina = numeroPagina;
        this.operazione = operazione;
    }

    public ProdottoSearchResultsDto() {
    }

    public List<Prodotto> getResult() {
        return result;
    }

    public void setResult(List<Prodotto> result) {
        this.result = result;
    }

    public int getnumeroPagina() {
        return numeroPagina;
    }

    public void setnumeroPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    public String getOperazione() {
        return operazione;
    }

    public void setOperazione(String operazione) {
        this.operazione = operazione;
    }

    

}
