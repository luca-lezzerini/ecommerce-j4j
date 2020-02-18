package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.Colori;
import com.ai.ecommercej4j.model.ColoriCreateDto;
import com.ai.ecommercej4j.model.ColoriDeleteDto;
import com.ai.ecommercej4j.model.ColoriSearchDto;
import com.ai.ecommercej4j.model.ColoriSearchResultsDto;
import com.ai.ecommercej4j.model.ColoriUpdateDto;
import com.ai.ecommercej4j.repository.ColoriRepository;
import com.ai.ecommercej4j.service.ColoriService;
import com.ai.ecommercej4j.service.SecurityService;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColoriServiceImpl implements ColoriService {

    @Autowired
    private ColoriRepository coloriRepository;

    @Autowired
    private SecurityService securityService;

    @Override
    public void createColori(ColoriCreateDto dto) {
        if (securityService.checkToken(dto.getToken())) {
            Colori colore = new Colori();
            colore.setDescrizione(dto.getDati().getDescrizione());
            colore.setCodice(dto.getDati().getCodice());
            coloriRepository.save(colore);
        } else {
            System.out.println("il token non esiste");
        }
    }

    @Override
    public ColoriSearchResultsDto searchColori(ColoriSearchDto dto) {
        List<Colori> ace = null;
        ColoriSearchResultsDto dto2 = new ColoriSearchResultsDto(ace);
        if (securityService.checkToken(dto.getToken())) {
            // se la stringa ricevuta è vuota recupera dal db tutti gli elementi...
            if ((dto.getSearchKey()).equals("")) {
                ace = coloriRepository.findAll();
                // ... altrimenti recupera solo quelli il cui codice è quello cercato
            } else {
                ace = coloriRepository.findByCodiceContainingIgnoreCase(dto.getSearchKey());
            }

            return dto2;
        } else {
           dto2.setResult(Collections.emptyList());
        }
        return dto2;
    }

    @Override
    public void deleteColori(ColoriDeleteDto dto) {
        coloriRepository.deleteById(dto.getIdToDelete());
    }

    @Override
    public void updateColori(ColoriUpdateDto dto) {
        Colori c = coloriRepository.findById(dto.getDati().getId()).orElse(null);
        c.setCodice(dto.getDati().getCodice());
        c.setDescrizione(dto.getDati().getDescrizione());
        coloriRepository.save(c);
    }

    @Override
    public ColoriSearchResultsDto searchColoriPerDescrizione(ColoriSearchDto dto) {
        List<Colori> ace;
        //se la stringa è vuota recupera dal db tutti gli elementi
        if ((dto.getSearchKey()).equals("")) {
            ace = coloriRepository.findAll();
        } else {
            ace = coloriRepository.findByDescrizioneContainingIgnoreCase(dto.getSearchKey());
        }
        ColoriSearchResultsDto dto2 = new ColoriSearchResultsDto(ace);
        return dto2;
    }
}
