package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.TagliaCreateDto;
import com.ai.ecommercej4j.model.TagliaDeleteDto;
import com.ai.ecommercej4j.model.TagliaSearchDto;
import com.ai.ecommercej4j.model.TagliaSearchResultsDto;
import com.ai.ecommercej4j.model.TagliaUpdateDto;
import com.ai.ecommercej4j.repository.TagliaRepository;
import com.ai.ecommercej4j.service.SecurityService;
import com.ai.ecommercej4j.service.TagliaService;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TagliaServiceImpl implements TagliaService {

    private final int PAGE_DIMENSION = 5;

    @Autowired
    private SecurityService ss;

    @Autowired
    private TagliaRepository tr;

    @Override
    public void createTaglia(TagliaCreateDto dto) {
        //verifico che il token sia registrato...
        if (ss.checkToken(dto.getToken())) {
            //...se è registrato verifico che non esista una taglia con lo stesso codice...   
            if (tr.findByCodiceIgnoreCase(dto.getDati().getCodice()) == null) {
                //...se non esiste la salvo nel db
                tr.save(dto.getDati());
            }
            //...se esiste non faccio nulla
        }
        //...se il token non è registrato non faccio nulla
    }

    @Override
    public TagliaSearchResultsDto searchTaglia(TagliaSearchDto dto) {
        TagliaSearchResultsDto result = new TagliaSearchResultsDto();
        String ricerca = dto.getSearchKey();
        int pagina = dto.getPage();
        // TODO : ottenere ultima pagina se viene passato dal dto -1
/*        if (pagina == -1) {
            pagina = ((int) tr.count()) / PAGE_DIMENSION;
            if (((int) tr.count()) != PAGE_DIMENSION * pagina) {
                pagina += 1;
            }
        }*/
        //verifico che il token sia registrato...
        if (ss.checkToken(dto.getToken())) {
            //...se è registrato controllo se la stringa di ricerca è vuota...
            if (ricerca.equals("")) {
                //...se è vuota ritorno tutte le taglie
                result.setResult(tr.findAll(generatePage(pagina)).toList());
            } else {
                //...atrimenti cerco solo quelle che soddisfano la ricerca
                result.setResult((tr.findByDescrizioneContainingIgnoreCase(dto.getSearchKey(), generatePage(pagina))).toList());
            }
            result.setPage(dto.getPage());
        } else {
            //...altrimenti ritorno una lista vuota 
            result.setResult(Collections.emptyList());
        }
        return result;
    }

    @Override
    public void deleteTaglia(TagliaDeleteDto dto) {
        //verifico che il token sia registrato...
        if (ss.checkToken(dto.getToken())) {
            //...se è registrato cancella la taglia tramite l'id
            tr.deleteById(dto.getidToDelete());
        }
    }

    @Override
    public void updateTaglia(TagliaUpdateDto dto) {
        //verifico che il token sia registrato...
        if (ss.checkToken(dto.getToken())) {
            //verifico se l'id è diverso da null
            if (tr.findById(dto.getDati().getId()) != null) {
                //...se si modifica la taglia nel db
                tr.save(dto.getDati());
            }
        }
    }

    @Override
    public TagliaSearchResultsDto searchTagliaPerDescrizione(TagliaSearchDto dto) {
        TagliaSearchResultsDto result = new TagliaSearchResultsDto();
        String ricerca = dto.getSearchKey();
        //verifico che il token sia registrato...
        if (ss.checkToken(dto.getToken())) {
            //...se è registrato controllo se la stringa di ricerca è vuota...
            if (ricerca.equals("")) {
                //...se è vuota ritorno tutte le taglie
                result.setResult(tr.findAll(generatePage(dto.getPage())).toList());
            } else {
                //...atrimenti cerco solo quelle che soddisfano la ricerca
                result.setResult((tr.findByDescrizioneContainingIgnoreCase(dto.getSearchKey(), generatePage(dto.getPage()))).toList());
            }
            result.setPage(dto.getPage());
        } else {
            //...altrimenti ritorno una lista vuota 
            result.setResult(Collections.emptyList());
        }
        return result;
    }

    private Pageable generatePage(int numeroPagina) {
        return PageRequest.of(numeroPagina, PAGE_DIMENSION);
    }
}
