package com.ai.ecommercej4j.model;

import java.util.List;


public class ColoriSearchResultsDto {
    private List<Colori> result;
    private int numeroPagina;
    private boolean ultimaPagina;

    public ColoriSearchResultsDto() {
    }

    public ColoriSearchResultsDto(List<Colori> result, int numeroPagina, boolean ultimaPagina) {
        this.result = result;
        this.numeroPagina = numeroPagina;
        this.ultimaPagina = ultimaPagina;
    }    

    public List<Colori> getResult() {
        return result;
    }

    public void setResult(List<Colori> result) {
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
