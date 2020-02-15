/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.Spedizione;
import com.ai.ecommercej4j.model.SpedizioneCreateDto;
import com.ai.ecommercej4j.model.SpedizioneDeleteDto;
import com.ai.ecommercej4j.model.SpedizioneSearchDto;
import com.ai.ecommercej4j.model.SpedizioneSearchResultsDto;
import com.ai.ecommercej4j.model.SpedizioneUpdateDto;
import com.ai.ecommercej4j.service.SpedizioneService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ai.ecommercej4j.repository.SpedizioneRepository;

@Service
public class SpedizioneServiceImpl implements SpedizioneService {

    @Autowired
    private SpedizioneRepository spedizioneRepository;

    @Override
    public void createSpedizione(SpedizioneCreateDto dto) {
        Spedizione spedizione = new Spedizione();
        spedizione.setId(spedizione.getId());
        spedizione.setCodice(spedizione.getCodice());
        spedizione.setDescrizione(spedizione.getDescrizione());
        spedizione.setPrezzo(spedizione.getPrezzo());
    }

    @Override
    public SpedizioneSearchResultsDto searchSpedizione(SpedizioneSearchDto dto) {
        List<Spedizione> ls = new ArrayList<>();
                SpedizioneSearchResultsDto dtosr = new SpedizioneSearchResultsDto(ls);

        return dtosr;
    }

    @Override
    public void deleteProdotto(SpedizioneDeleteDto dto) {
     //   spedizioneRepository.deleteById(dto.getIdToDelete());
    }

    @Override
    public void updateProdotto(SpedizioneUpdateDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //metodo per aggiornare/salvare dati previsto da repository
    }

}
