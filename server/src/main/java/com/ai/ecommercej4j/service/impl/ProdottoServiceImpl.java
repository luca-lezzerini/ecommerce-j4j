package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.Prodotto;
import com.ai.ecommercej4j.model.ProdottoCreateDto;
import com.ai.ecommercej4j.model.ProdottoDeleteDto;
import com.ai.ecommercej4j.model.ProdottoSearchDto;
import com.ai.ecommercej4j.model.ProdottoSearchResultsDto;
import com.ai.ecommercej4j.model.ProdottoUpdateDto;
import com.ai.ecommercej4j.repository.ProdottoRepository;
import com.ai.ecommercej4j.service.ProdottoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdottoServiceImpl implements ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Override
    public void createProdotto(ProdottoCreateDto dto) {

        // controllo se il dto esiste e se il codice del prodotto non è una stringa vuota
        if (dto != null && dto.getDati().getCodice().length() > 0) {

            // controllo se esiste già il codice del prodotto da creare
            List<Prodotto> lp = prodottoRepository.findByCodice(dto.getDati().getCodice());

            //se non esiste già, lo creo
            if (lp.isEmpty()) {
                prodottoRepository.save(dto.getDati());
            }
        }
    }

    @Override
    public ProdottoSearchResultsDto searchProdotto(ProdottoSearchDto dto) {
        ProdottoSearchResultsDto resultDto = new ProdottoSearchResultsDto();
        if (dto != null) {
            List<Prodotto> lp = prodottoRepository.findByCodice(dto.getSearchKey());
            resultDto.setResults(lp);
        }
        return resultDto;
    }

    @Override
    public void deleteProdotto(ProdottoDeleteDto dto) {
        if (dto != null) {
            prodottoRepository.deleteById(dto.getIdToDelete());
        }
    }

    @Override
    public void updateProdotto(ProdottoUpdateDto dto) {

        // controllo se il dto esiste e se il codice del prodotto non è una stringa vuota
        if (dto != null && dto.getDati().getCodice().length() > 0) {

            // cerco il prodotto da aggiornare
            List<Prodotto> lp = prodottoRepository.findByCodice(dto.getDati().getCodice());

            // se ho trovato qualcosa, recupero l'id del prodotto da aggiornare
            if (!lp.isEmpty()) {
                dto.getDati().setId(lp.get(0).getId());

                //aggiorno il prodotto sul db
                prodottoRepository.save(dto.getDati());
            }
        }
    }
}
