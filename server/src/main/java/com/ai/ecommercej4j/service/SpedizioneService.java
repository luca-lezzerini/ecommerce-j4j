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

// creo l'interfaccia del service spedizioni contenente i metodi utilizzati
public interface SpedizioneService {
    
    // richiamo il metodo crea spedizione dal service impl
    void createSpedizione(SpedizioneCreateDto dto);

    // richiamo il metodo cerca spedizione dal service impl
    SpedizioneSearchResultsDto searchSpedizione(SpedizioneSearchDto dto);

    // richiamo il metodo cancella spedizione dal service impl
    void deleteSpedizione(SpedizioneDeleteDto dto);

    // richiamo il metodo aggiorna spedizione dal service impl
    void updateSpedizione(SpedizioneUpdateDto dto);
    
    //richiamo il metodo ricerca per prezzo dal service impl
    SpedizioneSearchPrezzoResultsDto prezzoSearch(SpedizioneSearchPrezzoDto dto);
}



