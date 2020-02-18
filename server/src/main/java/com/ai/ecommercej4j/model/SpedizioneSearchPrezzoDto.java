/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.model;

public class SpedizioneSearchPrezzoDto extends LoginResponseDto {

   double searchKey;

    public SpedizioneSearchPrezzoDto(double searchKey, String token) {
        super(token);
        this.searchKey = searchKey;
    }

    public SpedizioneSearchPrezzoDto() {
    }

    public double getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(double searchKey) {
        this.searchKey = searchKey;
    }

}
