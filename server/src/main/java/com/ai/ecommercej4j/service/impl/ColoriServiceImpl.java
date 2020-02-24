package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.*;
import com.ai.ecommercej4j.repository.ColoriRepository;
import com.ai.ecommercej4j.service.ColoriService;
import com.ai.ecommercej4j.service.SecurityService;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ColoriServiceImpl implements ColoriService {

    @Autowired
    private ColoriRepository coloriRepository;
    
    @Autowired
    private SecurityService securityService;

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
    public ColoriSearchResultsDto searchColoriPerDescrizione(ColoriSearchDto dto) {

        // istanzio il dto di ritorno
        ColoriSearchResultsDto resultDto = new ColoriSearchResultsDto();

        // controllo se il dto e la chiave di ricerca sono diversi da null
        // e se il token è valido
        if (dto != null && dto.getSearchKey() != null
                && securityService.checkToken(dto.getToken())) {

            int paginaRichiesta;
            Pageable page;
            //trovo il numero totale delle pagine
            int numeroUltimaPagina = (int) ((coloriRepository.countByDescrizioneContaining(dto.getSearchKey()) - 1) / 5);

            //se la pagina che cerco è un numero positivo minore del numero totale di pagine...
            if (dto.getNumeroPagina() >= 0 && dto.getNumeroPagina() < numeroUltimaPagina) {
                paginaRichiesta = dto.getNumeroPagina();
            } else {
                // ... altrimenti la pagina che cerco è l'ultima
                paginaRichiesta = numeroUltimaPagina;
            }

            //preparo i dati della pagina da selezionare
            page = PageRequest.of(paginaRichiesta, 5, Sort.by("descrizione"));

            //recupero i risultati               
            Slice<Colori> sliceColore = coloriRepository.
                    findByDescrizioneContainingIgnoreCase(dto.getSearchKey(), page);

            //se ho ottenuto dei risultati, avvaloro il dto di ritorno...
            if (sliceColore != null && sliceColore.hasContent()) {
                resultDto.setNumeroPagina(sliceColore.getNumber());
                resultDto.setResult(sliceColore.getContent());
                resultDto.setUltimaPagina(sliceColore.isLast());

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
