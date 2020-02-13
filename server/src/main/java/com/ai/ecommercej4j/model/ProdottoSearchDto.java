/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.model;

/**
 *
 * @author utente
 */
public class ProdottoSearchDto extends LoginResponseDto {

    private String searchKey;

    public ProdottoSearchDto(String searchKey, String token) {
        super(token);
        this.searchKey = searchKey;
    }

    public ProdottoSearchDto() {
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

}
