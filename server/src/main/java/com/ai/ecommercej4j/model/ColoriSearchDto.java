package com.ai.ecommercej4j.model;

public class ColoriSearchDto extends LoginResponseDto{
    private String searchKey;

    public ColoriSearchDto() {
    }

    public ColoriSearchDto(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }
}
