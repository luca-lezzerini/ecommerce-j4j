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
    private int numeroPagina;
    
    public ProdottoSearchDto(String searchKey, String token, int numeroPagina) {
        super(token);
        this.searchKey = searchKey;
        this.numeroPagina = numeroPagina;
    }

    public ProdottoSearchDto() {
    }
    
    
    
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
