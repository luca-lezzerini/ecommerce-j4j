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
import com.ai.ecommercej4j.repository.SpedizioneRepository;
import com.ai.ecommercej4j.service.SecurityService;
//import static java.lang.Double.NaN;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
      
        SpedizioneSearchResultsDto resultDto = new SpedizioneSearchResultsDto();
        
        
        //Verifico l'esistenza del dto in ingresso e la validità del token
        if (dto != null && dto.getSearchKey() != null && securityService.checkToken(dto.getToken())) 
        {
        
            int paginaRichiesta;
            Pageable page;
            
            //trovo il numero totale delle pagine
            int numeroUltimaPagina = (int) ((spedizioneRepository.countByCodiceContaining(dto.getSearchKey()) - 1) / 5);
            
            //se la pagina che cerco è un numero positivo minore del numero totale di pagine...
            if (dto.getNumeroPagina() >= 0 && dto.getNumeroPagina() < numeroUltimaPagina) {
                paginaRichiesta = dto.getNumeroPagina();
            } else {
                // ... altrimenti la pagina che cerco è l'ultima
                paginaRichiesta = numeroUltimaPagina;
            }

            //preparo i dati della pagina da selezionare
            page = PageRequest.of(paginaRichiesta, 5, Sort.by("codice"));

            //recupero i risultati               
            Slice<Spedizione> sliceSpedizione = spedizioneRepository.
                    findByCodiceContainingIgnoreCase(dto.getSearchKey(), page);

            //se ho ottenuto dei risultati, avvaloro il dto di ritorno...
            if (sliceSpedizione != null && sliceSpedizione.hasContent()) {
                resultDto.setNumeroPagina(sliceSpedizione.getNumber());
                resultDto.setResult(sliceSpedizione.getContent());
                resultDto.setUltimaPagina(sliceSpedizione.isLast());

            } else {
                // ... se non ci sono risultati, restituisce una List vuota
                resultDto.setResult(Collections.emptyList());
            }
        } else {
            // ... se il dto non esiste, restituisce una List vuota
            resultDto.setResult(Collections.emptyList());
        }
        return resultDto;
       
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
        //Metodo per cercare per prezzo le spedizioni nel repository
        System.out.println("server, ricerca per prezzo");
        List<Spedizione> gig = null;
        SpedizioneSearchPrezzoResultsDto dtoPr = new SpedizioneSearchPrezzoResultsDto();
        System.out.println("dto creato");
        //Verifico che la searchKey non sia uguale a null
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
