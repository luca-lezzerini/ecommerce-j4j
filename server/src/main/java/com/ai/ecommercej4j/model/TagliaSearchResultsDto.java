package com.ai.ecommercej4j.model;

import java.util.ArrayList;
import java.util.List;

public class TagliaSearchResultsDto {

    private List<Taglia> result;
    private int page;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public TagliaSearchResultsDto() {
        result = new ArrayList<>();
    }

    public TagliaSearchResultsDto(int page) {
        result = new ArrayList<>();
        this.page = page;
    }

    public List<Taglia> getResult() {
        return result;
    }

    public void setResult(List<Taglia> result) {
        this.result = result;
    }
}
