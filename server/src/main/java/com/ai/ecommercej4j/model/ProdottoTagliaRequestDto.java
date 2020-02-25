package com.ai.ecommercej4j.model;

public class ProdottoTagliaRequestDto {
    
    private Prodotto prodotto;
    private Taglia taglia;

    public ProdottoTagliaRequestDto(Prodotto prodotto, Taglia taglia) {
        this.prodotto = prodotto;
        this.taglia = taglia;
    }

    public ProdottoTagliaRequestDto() {
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public Taglia getTaglia() {
        return taglia;
    }

    public void setTaglia(Taglia taglia) {
        this.taglia = taglia;
    }
    
}
