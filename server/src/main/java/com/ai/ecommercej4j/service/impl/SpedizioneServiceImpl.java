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
import com.ai.ecommercej4j.model.SpedizioneSearchPrezzoDto;
import com.ai.ecommercej4j.model.SpedizioneSearchPrezzoResultsDto;
import com.ai.ecommercej4j.model.SpedizioneSearchResultsDto;
import com.ai.ecommercej4j.model.SpedizioneUpdateDto;
import com.ai.ecommercej4j.service.SpedizioneService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ai.ecommercej4j.repository.SpedizioneRepository;
import com.ai.ecommercej4j.service.SecurityService;
import static java.lang.Double.NaN;
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
        System.out.println("server, createdto");
        if (securityService.checkToken(dto.getToken())) {
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
        System.out.println("server, searchdto");
        SpedizioneSearchResultsDto dtossr = new SpedizioneSearchResultsDto();
        //Verifico l'esistenza del dto in ingresso e la validità del token
        if (dto != null && securityService.checkToken(dto.getToken())) {
            System.out.println("token " + dto.getToken());
            List<Spedizione> ls = spedizioneRepository.findByCodiceContainingIgnoreCase(dto.getSearchKey());
             System.out.println(dto.getSearchKey());
            ls.forEach(ob -> {
                System.out.println("Codice" + ob.getCodice());
                System.out.println("Descrizione" + ob.getDescrizione());
                System.out.println("Prezzo" + ob.getPrezzo());
            }
            );
            // ordino i risultati per codice
            Collections.sort(ls, (s1, s2) -> s1.getCodice().compareTo(s2.getCodice()));
            dtossr.setResult(ls);
        } else {
            System.out.println("token non esiste" + dto.getToken());
            //Se non esiste restituisco lista vuota
            dtossr.setResult(Collections.emptyList());
        }
        return dtossr;
    }

    @Override
    public void deleteSpedizione(SpedizioneDeleteDto dto) {
        /* 1-Verifico se esiste già un dto rispetto a quello che ho 
         Elimino spedizione relativa all'ID*/
        System.out.println("server, deletedto");
        if (dto != null) {
            if (securityService.checkToken(dto.getToken())) {
                spedizioneRepository.deleteById(dto.getIdToDelete());
            }
        }
    }

    @Override
    public void updateSpedizione(SpedizioneUpdateDto dto) {
        //Metodo per aggiornare/salvare dati previsto da repository
        System.out.println("server, updatedto");
        // Verifico esistenza dto e codice spedizione non vuoto
        if (dto != null && dto.getDati() != null 
                && dto.getDati().getCodice() != null 
                && dto.getDati().getCodice().length() > 0) {
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

    @Override
    public SpedizioneSearchPrezzoResultsDto prezzoSearch(SpedizioneSearchPrezzoDto dtoPrezzo) {
        System.out.println("server, ricerca per prezzo");
        List<Spedizione> gig = null;
        SpedizioneSearchPrezzoResultsDto dtoPr = new SpedizioneSearchPrezzoResultsDto();
        System.out.println("dto creato");
        if (dtoPrezzo.getSearchKey()==null){
             gig=spedizioneRepository.findAll();
        }
        else if (dtoPrezzo.getSearchKey()>0){
        gig = spedizioneRepository.findByPrezzoLessThan(dtoPrezzo.getSearchKey());
        System.out.println(gig);
        System.out.println("SearchKey=" + dtoPrezzo.getSearchKey());
        }
        dtoPr.setResult(gig);
        return dtoPr;
    }
}
