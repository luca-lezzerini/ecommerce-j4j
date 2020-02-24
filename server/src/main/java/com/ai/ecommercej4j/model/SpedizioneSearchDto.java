/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.model;

// creo il data transfer object della ricerca delle spedizioni
public class SpedizioneSearchDto extends LoginResponseDto {

    String searchKey;
    private int numeroPagina;


    // inizializzo il dto con il costruttore della classe genitore 
    public SpedizioneSearchDto(String searchKey, String token , int numeroPagina) {
        super(token);
        this.searchKey = searchKey;
        this.numeroPagina = numeroPagina;
    }

    public SpedizioneSearchDto() {
    }

    // metto i getter e i setter
    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public int getNumeroPagina() {
        return numeroPagina;
    }

    public void setNumeroPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

}
