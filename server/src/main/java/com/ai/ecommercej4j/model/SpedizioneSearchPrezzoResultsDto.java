/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.model;

import java.util.List;

// creo il data transfer object dell'array delle spedizioni risultante dalla ricerca per prezzo
public class SpedizioneSearchPrezzoResultsDto extends LoginResponseDto  {
    private List<Spedizione> result;

  public SpedizioneSearchPrezzoResultsDto(List<Spedizione> results) {
        this.result = results;
    }

    public SpedizioneSearchPrezzoResultsDto() {
    }

    // metto i getter e i setter
    public List<Spedizione> getResult() {
        return result;
    }

    public void setResult(List<Spedizione> results) {
        this.result = results;
    }
}