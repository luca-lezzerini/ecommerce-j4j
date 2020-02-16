package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.Prodotto;
import com.ai.ecommercej4j.model.ProdottoCreateDto;
import com.ai.ecommercej4j.model.ProdottoDeleteDto;
import com.ai.ecommercej4j.model.ProdottoSearchDto;
import com.ai.ecommercej4j.model.ProdottoSearchResultsDto;
import com.ai.ecommercej4j.model.ProdottoUpdateDto;
import com.ai.ecommercej4j.repository.ProdottoRepository;
import com.ai.ecommercej4j.service.ProdottoService;
import com.ai.ecommercej4j.service.SecurityService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdottoServiceImpl implements ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Autowired
    private SecurityService securityService;

    /**
     * inserisce un nuovo Prodotto 
     * non viene inserito nei casi in cui: 
     * il parametro dto è null; 
     * la proprietà dati del dto è null;  
     * il codice del prodotto da inserire è una stringa vuota; 
     * il token non è valido; 
     * il codice del prodotto da inserire esiste già
     *
     * @param dto
     */
    @Override
    public void createProdotto(ProdottoCreateDto dto) {

        // controllo se il dto esiste e se il codice del prodotto non è una stringa vuota
        if (dto != null && dto.getDati() != null && dto.getDati().getCodice().length() > 0) {

            // controllo se il token esiste
            if (securityService.checkToken(dto.getToken())) {

                // controllo se esiste già il codice del prodotto da creare
                List<Prodotto> lp = prodottoRepository
                        .findByCodiceContainingIgnoreCase(dto.getDati().getCodice());

                // se non esiste già, lo creo
                if (lp.isEmpty()) {
                    prodottoRepository.save(dto.getDati());
                }
            }
        }
    }

    /**
     * ricerca Prodotto in base al valore della proprietà searchKey di dto.
     * restituisce un dto la cui proprietà results contiene un ArrayList di 
     * Prodotto, il cui codice contiene la searchKey
     * restituisce una lista vuota se il parametro dto è null, o se non trova 
     * risultati, o se il token non è valido
     *
     * @param dto
     * @return ArrayList<Prodotto>
     */
    @Override
    public ProdottoSearchResultsDto searchProdotto(ProdottoSearchDto dto) {

        // istanzio il dto di ritorno
        ProdottoSearchResultsDto resultDto = new ProdottoSearchResultsDto();

        // controllo se il dto in ingresso esiste...
        if (dto != null) {

            // controllo se il token esiste            
            if (securityService.checkToken(dto.getToken())) {

                //recupero i risultati e avvaloro il dto di ritorno                
                List<Prodotto> lp = prodottoRepository.
                        findByCodiceContainingIgnoreCase(dto.getSearchKey());
                resultDto.setResults(lp);
            }
        } else {
            // ... se il dto non esiste, restituisce un ArrayList vuoto
            resultDto.setResults(new ArrayList<>());
        }
        return resultDto;
    }

    /**
     * elimina un Prodotto avente id uguale alla proprietà idToDelete del dto.
     * non elimina nulla se il parametro dto è null, o se il token non è valido,
     * o se la proprietà idToDelete del dto è null
     * 
     * @param dto 
     */
    @Override
    public void deleteProdotto(ProdottoDeleteDto dto) {

        // controllo se il dto in ingresso esiste
        if (dto != null) {

            // controllo se il token esiste
            if (securityService.checkToken(dto.getToken())) {

            }
            // controllo se l'id del dto non è vuoto                
            if (dto.getIdToDelete() != null) {

                // elimino il prodotto
                prodottoRepository.deleteById(dto.getIdToDelete());
            }
        }
    }

    /**
     * modifica i valori di un Prodotto corrispondente che ha id uguale a
     * dto.dati.id
     * non esegue la modifica se il dto o dto.dati sono null, o se dto.codice è 
     * null o stringa vuota, o se il token non è valido, o se dto.dati.id è una 
     * stringa vuota
     * 
     * @param dto 
     */
    @Override
    public void updateProdotto(ProdottoUpdateDto dto) {

        // controllo se il dto esiste e se il codice del prodotto non è una stringa vuota
        if (dto != null && dto.getDati() != null && dto.getDati().getCodice().length() > 0) {

            // controllo se il token esiste
            if (securityService.checkToken(dto.getToken())) {

                // controllo se l'id del dto non è vuoto                
                if (dto.getDati().getId() != null) {

                    // aggiorno il prodotto
                    prodottoRepository.save(dto.getDati());
                }
            }
        }
    }
}
