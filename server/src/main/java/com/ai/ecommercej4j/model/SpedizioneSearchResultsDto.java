
package com.ai.ecommercej4j.model;

import java.util.List;

public class SpedizioneSearchResultsDto extends LoginResponseDto  {
    private List<Spedizione> result;

  public SpedizioneSearchResultsDto(List<Spedizione> results) {
        this.result = results;
    }

    public SpedizioneSearchResultsDto() {
    }

    public List<Spedizione> getResults() {
        return result;
    }

    public void setResults(List<Spedizione> results) {
        this.result = results;
    }
}