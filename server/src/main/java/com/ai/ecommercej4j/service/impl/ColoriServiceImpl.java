package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.*;
import com.ai.ecommercej4j.repository.ColoriRepository;
import com.ai.ecommercej4j.service.ColoriService;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import com.ai.ecommercej4j.service.SecurityService;
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

        // istanzio il dto di risposta
        ColoriSearchResultsDto rdto = new ColoriSearchResultsDto();

        int paginaRichiesta;
        Pageable pagina;
        // numero totale di elementi diviso numero di elementi per pagina (quindi ultima pagina)
        int numeroUltimaPagina = (int) ((coloriRepository.countByDescrizioneContaining(dto.getSearchKey()) - 1) / 5);

        //se la pagina che sto cercando è un numero positivo minore del numero totale di pagine...
        if (dto.getNumeroPagina() >= 0 && dto.getNumeroPagina() < numeroUltimaPagina) {
            paginaRichiesta = dto.getNumeroPagina();
        } else {
            // ... altrimenti sto cercando l'ultima pagina
            paginaRichiesta = numeroUltimaPagina;
        }

        //preparo i dati della pagina da selezionare, ordinati per codice
        pagina = PageRequest.of(paginaRichiesta, 5, Sort.by("codice"));

        //recupero i risultati               
        Slice<Colori> sliceColore = coloriRepository.
                findByCodiceOrDescrizioneContainingIgnoreCase(dto.getSearchKey(), dto.getSearchKey(), pagina);

        //se ottengo dei risultati, li salvo nel dto
        if (sliceColore != null && sliceColore.hasContent()) {
            rdto.setNumeroPagina(sliceColore.getNumber());
            rdto.setResult(sliceColore.getContent());
            rdto.setUltimaPagina(sliceColore.isLast());

        } else {
            // ... altrimenti ritorno una lista vuota
            rdto.setResult(Collections.emptyList());
        }

        return rdto;
    }

    @Override
    public void deleteColori(ColoriDeleteDto dto) {
        coloriRepository.deleteById(dto.getIdToDelete());
    }

    @Override
    public void updateColori(ColoriUpdateDto dto) {
        // ricerco l'id, se non lo trovo il valore è null
        Colori c = coloriRepository.findById(dto.getDati().getId()).orElse(null);
        c.setCodice(dto.getDati().getCodice());
        c.setDescrizione(dto.getDati().getDescrizione());
        coloriRepository.save(c);
    }

}
