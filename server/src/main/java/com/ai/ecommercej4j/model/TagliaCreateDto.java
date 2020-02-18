package com.ai.ecommercej4j.model;

public class TagliaCreateDto extends LoginResponseDto {

    private Taglia dati;

    public TagliaCreateDto() {
    }

    public TagliaCreateDto(Taglia dati, String token) {
        super(token);
        this.dati = dati;
    }

    public Taglia getDati() {
        return dati;
    }

    public void setDati(Taglia dati) {
        this.dati = dati;
    }
    

}
