/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.model;

// creo il data transfer object della creazione delle spedizioni
public class SpedizioneCreateDto extends LoginResponseDto {

    Spedizione dati;

    public SpedizioneCreateDto() {
    }
    
    public SpedizioneCreateDto(Spedizione dati, String token) {
        super(token);
        this.dati = dati;
    }
    
    // metto i getter e i setter
    public Spedizione getDati() {
        return dati;
    }

    public void setDati(Spedizione dati) {
        this.dati = dati;
    }

}
