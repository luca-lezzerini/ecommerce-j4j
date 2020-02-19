
package com.ai.ecommercej4j.model;

import java.util.List;

// creo il data transfer object della ricerca delle spedizioni
public class SpedizioneSearchResultsDto extends LoginResponseDto  {
    private List<Spedizione> result;

  public SpedizioneSearchResultsDto(List<Spedizione> results) {
        this.result = results;
    }

    public SpedizioneSearchResultsDto() {
    }

    // metto i getter e i setter
    public List<Spedizione> getResult() {
        return result;
    }

    public void setResult(List<Spedizione> results) {
        this.result = results;
    }
}