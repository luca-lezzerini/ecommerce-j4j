/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.model;

// creo il data transfer object della ricerca tramite prezzo delle spedizioni
public class SpedizioneSearchPrezzoDto extends LoginResponseDto {

   Double searchKey;

   // inizializzo il dto con il costruttore della classe genitore 
    public SpedizioneSearchPrezzoDto(Double searchKey, String token) {
        super(token);
        this.searchKey = searchKey;
    }

    public SpedizioneSearchPrezzoDto() {
    }

    // metto i getter e i setter
    public Double getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(Double searchKey) {
        this.searchKey = searchKey;
    }

}
