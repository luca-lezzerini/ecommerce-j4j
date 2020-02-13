package com.ai.ecommercej4j.model;

public class ColoriCreateDto extends LoginResponseDto{
    private Colori dati;

    public ColoriCreateDto(Colori dati) {
        this.dati = dati;
    }

    public ColoriCreateDto() {
    }

    public Colori getDati() {
        return dati;
    }

    public void setDati(Colori dati) {
        this.dati = dati;
    }
}
