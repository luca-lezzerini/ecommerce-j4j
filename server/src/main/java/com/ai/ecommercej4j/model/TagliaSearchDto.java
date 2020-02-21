package com.ai.ecommercej4j.model;

public class TagliaSearchDto extends LoginResponseDto {

    private String searchKey;
    private int page;

    public TagliaSearchDto(String searchKey, String token, int page) {
        super(token);
        this.searchKey = searchKey;
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
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
