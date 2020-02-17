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
            if (spedizioneRepository.findByCodice(dto.getDati().getCodice()) == null) {
                //Dopo aver verificato l'esistenza,salvo nel database
                spedizioneRepository.save(dto.getDati());
            }
        }
    }

    @Override
    public SpedizioneSearchResultsDto searchSpedizione(SpedizioneSearchDto dto) {
        List<Spedizione> ls = spedizioneRepository.findByCodice(dto.getSearchKey());
        SpedizioneSearchResultsDto dtossr = new SpedizioneSearchResultsDto(ls);
        return dtossr;
    }

    @Override
    public void deleteSpedizione(SpedizioneDeleteDto dto) {
        /* 1-Verifico se esiste già un dto rispetto a quello che ho 
         Elimino spedizione relativa all'ID*/
        if (securityService.checkToken(dto.getToken())) {
            spedizioneRepository.deleteById(dto.getIdToDelete());
        }
    }

    @Override
    public void updateSpedizione(SpedizioneUpdateDto dto) {
        //Metodo per aggiornare/salvare dati previsto da repository

        // Verifico esistenza dto e codice spedizione non vuoto
        while (dto != null && dto.getDati() != null && dto.getDati().getCodice() != null) {
            // Verifico vaalidità token
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
