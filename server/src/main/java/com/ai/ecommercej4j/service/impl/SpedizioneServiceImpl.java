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
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ai.ecommercej4j.repository.SpedizioneRepository;
import com.ai.ecommercej4j.service.SecurityService;
import java.util.Collections;

@Service
public class SpedizioneServiceImpl implements SpedizioneService {

    @Autowired
    private SpedizioneRepository spedizioneRepository;
    @Autowired
    private SecurityService securityService;

    @Override
    public void createSpedizione(SpedizioneCreateDto dto) {
        //Verifica esistenza token
        while (securityService.checkToken(dto.getToken())) {
            if (spedizioneRepository.findByCodiceContainingIgnoreCase(dto.getDati().getCodice()) != null) {
                List<Spedizione> ls = spedizioneRepository
                        .findByCodiceContainingIgnoreCase(dto.getDati().getCodice());
                //Dopo aver verificato l'esistenza o meno,salvo nel database
                if (ls.isEmpty()) {
                    spedizioneRepository.save(dto.getDati());
                }
            }
        }
    }

    @Override
    public SpedizioneSearchResultsDto searchSpedizione(SpedizioneSearchDto dto) {
        SpedizioneSearchResultsDto dtossr = new SpedizioneSearchResultsDto();
        //Verifico l'esistenza del dto in ingresso e la validità del token
        if (dto != null && securityService.checkToken(dto.getToken())) {

            List<Spedizione> ls = spedizioneRepository.findByCodiceContainingIgnoreCase(dto.getSearchKey());
        } else {
            //Se non esiste restituisco lista vuota
            dtossr.setResults(Collections.emptyList());
        }
        return dtossr;
    }

    @Override
    public void deleteSpedizione(SpedizioneDeleteDto dto) {
        /* 1-Verifico se esiste già un dto rispetto a quello che ho 
         Elimino spedizione relativa all'ID*/
        if (dto != null) {
            if (securityService.checkToken(dto.getToken())) {
                spedizioneRepository.deleteById(dto.getIdToDelete());
            }
        }
    }

    @Override
    public void updateSpedizione(SpedizioneUpdateDto dto) {
        //Metodo per aggiornare/salvare dati previsto da repository

        // Verifico esistenza dto e codice spedizione non vuoto
        while (dto != null && dto.getDati() != null && dto.getDati().getCodice() != null) {
            // Verifico validità token
            if (securityService.checkToken(dto.getToken())) {
                // controllo se l'id del dto non è vuoto                
                if (dto.getDati().getId() != null) {
                    // update spedizione
                    spedizioneRepository.save(dto.getDati());
                }
            }
        }
    }

}
