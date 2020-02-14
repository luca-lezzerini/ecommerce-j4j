package com.ai.ecommercej4j.model;

public class TagliaSearchDto extends LoginResponseDto {

    private String searchKey;

    public TagliaSearchDto(String searchKey, String token) {
        super(token);
        this.searchKey = searchKey;
    }

    public TagliaSearchDto() {
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

}
