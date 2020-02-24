package com.ai.ecommercej4j.model;

public class ColoriSearchDto extends LoginResponseDto {

    private String searchKey;
    private int numeroPagina;

    public ColoriSearchDto() {
    }

    public ColoriSearchDto(String searchKey, int numeroPagina) {
        this.searchKey = searchKey;
        this.numeroPagina = numeroPagina;
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
