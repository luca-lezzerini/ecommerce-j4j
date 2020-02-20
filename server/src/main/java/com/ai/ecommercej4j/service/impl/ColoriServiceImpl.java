package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.*;
import com.ai.ecommercej4j.repository.ColoriRepository;
import com.ai.ecommercej4j.service.ColoriService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColoriServiceImpl implements ColoriService {

    @Autowired
    private ColoriRepository coloriRepository;

    @Override
    public void createColori(ColoriCreateDto dto) {
        Colori colore = new Colori();
        colore.setDescrizione(dto.getDati().getDescrizione());
        colore.setCodice(dto.getDati().getCodice());
        coloriRepository.save(colore);
    }

    @Override
    public ColoriSearchResultsDto searchColori(ColoriSearchDto dto) {
        List<Colori> ace = null;
        // se la stringa ricevuta è vuota recupera dal db tutti gli elementi...
        if ((dto.getSearchKey()).equals("")) {
            ace = coloriRepository.findAll();
            // ... altrimenti recupera solo quelli il cui codice è quello cercato
        } else {
            ace = coloriRepository.findByCodiceContainingIgnoreCase(dto.getSearchKey());
            ace.addAll(coloriRepository.findByDescrizioneContainingIgnoreCase(dto.getSearchKey()));
        }
        ColoriSearchResultsDto dto2 = new ColoriSearchResultsDto(ace);
        return dto2;
    }

    @Override
    public void deleteColori(ColoriDeleteDto dto) {
        coloriRepository.deleteById(dto.getIdToDelete());
    }

    @Override
    public void updateColori(ColoriUpdateDto dto) {
        // ricerco l'id se non lo trovo il valore è null
        Colori c = coloriRepository.findById(dto.getDati().getId()).orElse(null);
        c.setCodice(dto.getDati().getCodice());
        c.setDescrizione(dto.getDati().getDescrizione());
        coloriRepository.save(c);
    }

}
