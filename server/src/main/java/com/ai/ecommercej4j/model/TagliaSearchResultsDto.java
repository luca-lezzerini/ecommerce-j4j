package com.ai.ecommercej4j.model;

import java.util.ArrayList;
import java.util.List;

public class TagliaSearchResultsDto {

    private List<Taglia> result;

    public TagliaSearchResultsDto() {
        result = new ArrayList<>();
    }

    public List<Taglia> getResult() {
        return result;
    }

    public void setResult(List<Taglia> result) {
        this.result = result;
    }
}
