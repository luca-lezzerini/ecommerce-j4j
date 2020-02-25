package com.ai.ecommercej4j.model;

import java.util.List;

public class ProdottoTagliaRequestDto {
    
    private Prodotto prodotto;
    private List<Taglia> taglia;

    public ProdottoTagliaRequestDto() {
    }

    public ProdottoTagliaRequestDto(Prodotto prodotto, List<Taglia> taglia) {
        this.prodotto = prodotto;
        this.taglia = taglia;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public List<Taglia> getTaglia() {
        return taglia;
    }

    public void setTaglia(List<Taglia> taglia) {
        this.taglia = taglia;
    }

    
}
