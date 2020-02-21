package com.ai.ecommercej4j.model;

import java.util.ArrayList;
import java.util.List;

public class TagliaSearchResultsDto {

    private List<Taglia> result;
    private int page;
    private boolean first;
    private boolean last;

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public TagliaSearchResultsDto(int page, boolean first, boolean last) {
        this.page = page;
        this.first = first;
        this.last = last;
    }

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
