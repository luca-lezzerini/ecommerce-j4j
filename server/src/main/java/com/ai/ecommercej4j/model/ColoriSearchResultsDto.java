package com.ai.ecommercej4j.model;

import java.util.List;


public class ColoriSearchResultsDto {
    private List<Colori> result;

    public ColoriSearchResultsDto(List<Colori> result) {
        this.result = result;
    }

    public List<Colori> getResult() {
        return result;
    }

    public void setResult(List<Colori> result) {
        this.result = result;
    }
}
