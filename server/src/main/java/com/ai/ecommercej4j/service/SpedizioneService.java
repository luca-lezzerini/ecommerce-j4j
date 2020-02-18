/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.service;

import com.ai.ecommercej4j.model.SpedizioneCreateDto;
import com.ai.ecommercej4j.model.SpedizioneDeleteDto;
import com.ai.ecommercej4j.model.SpedizioneSearchDto;
import com.ai.ecommercej4j.model.SpedizioneSearchPrezzoDto;
import com.ai.ecommercej4j.model.SpedizioneSearchPrezzoResultsDto;
import com.ai.ecommercej4j.model.SpedizioneSearchResultsDto;
import com.ai.ecommercej4j.model.SpedizioneUpdateDto;

/**
 *
 * @author feder
 */
public interface SpedizioneService {
       
    void createSpedizione(SpedizioneCreateDto dto);

    SpedizioneSearchResultsDto searchSpedizione(SpedizioneSearchDto dto);

    void deleteSpedizione(SpedizioneDeleteDto dto);

    void updateSpedizione(SpedizioneUpdateDto dto);
    
    SpedizioneSearchPrezzoResultsDto prezzoSearch(SpedizioneSearchPrezzoDto dto);
}

    

