package com.ai.ecommercej4j.model;

import org.springframework.data.domain.Page;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author utente
 */
public class SpedizionePageSearchResultsDto {
    private Page <Spedizione> result;

    public SpedizionePageSearchResultsDto(Page<Spedizione> result) {
        this.result = result;
    }

    public SpedizionePageSearchResultsDto() {
    }

    public Page<Spedizione> getResult() {
        return result;
    }

    public void setResult(Page<Spedizione> result) {
        this.result = result;
    }
    
    
}
