/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.model;

// creo il data transfer object che aggiorna le spedizioni
public class SpedizioneUpdateDto extends SpedizioneCreateDto {

    // inizializzo il dto con il costruttore della classe genitore 
    public SpedizioneUpdateDto(Spedizione dati, String token) {
         super(dati, token);
    }

}
