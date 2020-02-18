/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.model;

public class SpedizioneSearchPrezzoDto extends LoginResponseDto {

   Double searchKey;

    public SpedizioneSearchPrezzoDto(Double searchKey, String token) {
        super(token);
        this.searchKey = searchKey;
    }

    public SpedizioneSearchPrezzoDto() {
    }

    public Double getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(Double searchKey) {
        this.searchKey = searchKey;
    }

}
