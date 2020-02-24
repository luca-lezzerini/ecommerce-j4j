
package com.ai.ecommercej4j.model;

import java.util.List;

// creo il data transfer object della ricerca delle spedizioni
public class SpedizioneSearchResultsDto extends LoginResponseDto  {
    private List<Spedizione> result;
    private int numeroPagina;
    private boolean ultimaPagina;
    
    public SpedizioneSearchResultsDto(List<Spedizione> result, int numeroPagina, boolean ultimaPagina) {
        this.result = result;
        this.numeroPagina = numeroPagina;
        this.ultimaPagina = ultimaPagina;
    }
   

    public SpedizioneSearchResultsDto() {
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

    // metto i getter e i setter
    public List<Spedizione> getResult() {
        return result;
    }

    public void setResult(List<Spedizione> results) {
        this.result = results;
    }
}