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
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdottoServiceImpl implements ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Autowired
    private SecurityService securityService;
    
    @Override
    public void createProdotto(ProdottoCreateDto dto) {

        // controllo se il dto esiste e se il codice del prodotto non è una stringa vuota
        if (dto != null && dto.getDati().getCodice().length() > 0) {

            // controllo se il token esiste
            if (securityService.checkToken(dto.getToken())) {

                // controllo se esiste già il codice del prodotto da creare
                List<Prodotto> lp = prodottoRepository.findByCodice(dto.getDati().getCodice());

                // se non esiste già, lo creo
                if (lp.isEmpty()) {
                    prodottoRepository.save(dto.getDati());
                }
            }
        }
    }

    @Override
    public ProdottoSearchResultsDto searchProdotto(ProdottoSearchDto dto) {

        // istanzio il dto di ritorno
        ProdottoSearchResultsDto resultDto = new ProdottoSearchResultsDto();

        // controllo se il dto in ingresso esiste
        if (dto != null) {
            // controllo se il token esiste
            if(true){
            //TODO riabilita if
            //if (securityService.checkToken(dto.getToken())) {
                //recupero i risultati e avvaloro il dto di ritorno                
                List<Prodotto> lp = prodottoRepository.findByCodice(dto.getSearchKey());
                resultDto.setResults(lp);
            }
        }

        // se non trovo nulla il dto è vuoto
        return resultDto;
    }

    @Override
    public void deleteProdotto(ProdottoDeleteDto dto) {

        // controllo se il dto in ingresso esiste
        if (dto != null) {

            // controllo se il token esiste ed elimino il prodotto
            if (securityService.checkToken(dto.getToken())) {
                prodottoRepository.deleteById(dto.getIdToDelete());
            }
        }
    }

    @Override
    public void updateProdotto(ProdottoUpdateDto dto) {
        createProdotto(dto);
    }
}
