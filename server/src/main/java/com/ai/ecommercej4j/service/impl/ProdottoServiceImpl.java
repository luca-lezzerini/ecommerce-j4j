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
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProdottoServiceImpl implements ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Autowired
    private SecurityService securityService;

    @Override
    public void createProdotto(ProdottoCreateDto dto) {

        // controllo validità del dto e dei suoi dati
        if (dto != null && dto.getDati() != null
                && dto.getDati().getCodice() != null
                && dto.getDati().getCodice().length() > 0) {

            // controllo se il token è valido
            if (securityService.checkToken(dto.getToken())) {

                // controllo se esiste già il codice del prodotto da creare
                List<Prodotto> lp = prodottoRepository
                        .findByCodiceIgnoreCase(dto.getDati().getCodice());

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

        // controllo se il dto e la chiave di ricerca sono diversi da null
        // e se il token è valido
        if (dto != null && dto.getSearchKey() != null
                && securityService.checkToken(dto.getToken())) {

            int paginaRichiesta;
            Pageable page;
            //trovo il numero totale delle pagine
            int numeroUltimaPagina = (int) ((prodottoRepository.countByCodiceContaining(dto.getSearchKey()) - 1) / 5);
            
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
            Slice<Prodotto> sliceProdotto = prodottoRepository.
                    findByCodiceContainingIgnoreCase(dto.getSearchKey(), page);

            //se ho ottenuto dei risultati, avvaloro il dto di ritorno...
            if (sliceProdotto != null && sliceProdotto.hasContent()) {
                resultDto.setNumeroPagina(sliceProdotto.getNumber());
                resultDto.setResult(sliceProdotto.getContent());
                resultDto.setUltimaPagina(sliceProdotto.isLast());

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
    public void deleteProdotto(ProdottoDeleteDto dto) {

        // controllo se il dto in ingresso esiste
        if (dto != null) {

            // controllo se il token è valido
            if (securityService.checkToken(dto.getToken())) {

            }
            // controllo se l'id del dto non è null                
            if (dto.getIdToDelete() != null) {

                // elimino il prodotto
                prodottoRepository.deleteById(dto.getIdToDelete());
            }
        }
    }

    @Override
    public void updateProdotto(ProdottoUpdateDto dto) {

        // controllo validità del dto e dei suoi dati
        if (dto != null && dto.getDati() != null
                && dto.getDati().getCodice() != null
                && dto.getDati().getCodice().length() > 0) {

            // controllo se il token è valido
            if (securityService.checkToken(dto.getToken())) {

                // controllo se l'id del dto non è vuoto                
                if (dto.getDati().getId() != null) {

                    // aggiorno il prodotto
                    prodottoRepository.save(dto.getDati());
                }
            }
        }
    }

    @Override
    public ProdottoSearchResultsDto searchOfferte(ProdottoSearchDto dto) {

        // istanzio il dto di ritorno
        ProdottoSearchResultsDto resultDto = new ProdottoSearchResultsDto();

        // controllo se il dto e la chiave di ricerca sono diversi da null
        // e se il token è valido
        if (dto != null && dto.getSearchKey() != null
                && securityService.checkToken(dto.getToken())) {
            List<Prodotto> lp;
            // se la key che riceve è vuota restituisce tutti i prodotti in offerta...
            if ((dto.getSearchKey().trim()).equals(Double.MAX_VALUE)) {
                lp = prodottoRepository.findByOfferta(true);
                //...altrimenti ricerca i prodotti in offerta con prezzo inferiore a quello dato
            } else {
                //trasformo la chiave di ricerca da string a double
                double prezzo = Double.parseDouble(dto.getSearchKey());

                //recupero i risultati e avvaloro il dto di ritorno
                lp = prodottoRepository.
                        findByPrezzoLessThanEqualAndOfferta(prezzo, true);

                // ordino i risultati per codice
//                Collections.sort(lp,(p1, p2) -> p1.getPrezzo().compareTo(p2.getPrezzo()));
            }
            resultDto.setResult(lp);

        } else {
            // ... se il dto non esiste, restituisce un ArrayList vuoto
            resultDto.setResult(Collections.emptyList());
        }
        return resultDto;
    }

    @Override
    public ProdottoSearchResultsDto searchProdottoPerDescrizione(ProdottoSearchDto dto) {

        // istanzio il dto di ritorno
        ProdottoSearchResultsDto resultDto = new ProdottoSearchResultsDto();

        // controllo se il dto e la chiave di ricerca sono diversi da null
        // e se il token è valido
        if (dto != null && dto.getSearchKey() != null) {

            //recupero i risultati e avvaloro il dto di ritorno                
            List<Prodotto> lp = prodottoRepository.
                    findByDescrizioneContainingIgnoreCase(dto.getSearchKey());

            // ordino i risultati per codice
            Collections.sort(lp, (p1, p2) -> p1.getCodice().compareTo(p2.getCodice()));
            resultDto.setResult(lp);
        } else if (dto.getSearchKey() == null && dto.getSearchKey() == "") {
            //recupero i risultati e avvaloro il dto di ritorno                
            List<Prodotto> lp = prodottoRepository.
                    findByDescrizioneContainingIgnoreCase(dto.getSearchKey());

            // ordino i risultati per codice
            Collections.sort(lp, (p1, p2) -> p1.getCodice().compareTo(p2.getCodice()));
            resultDto.setResult(lp);
        } else {
            // ... se il dto non esiste, restituisce un ArrayList vuoto
            resultDto.setResult(Collections.emptyList());
            System.out.println("impossibile cercare");
        }
        return resultDto;
    }

    @Override
    public ProdottoSearchResultsDto searchProdottoRiservato(ProdottoSearchDto dto) {
        // istanzio il dto di ritorno
        ProdottoSearchResultsDto resultDto = new ProdottoSearchResultsDto();

        // controllo se il dto e la chiave di ricerca sono diversi da null
        // e se il token è valido
        if (dto != null && dto.getSearchKey() != null
                && securityService.checkToken(dto.getToken())) {

            //recupero i risultati e avvaloro il dto di ritorno                
            List<Prodotto> lp = prodottoRepository.
                    findByCodiceContainingIgnoreCase(dto.getSearchKey());

            // ordino i risultati per codice
            Collections.sort(lp, (p1, p2) -> p1.getCodice().compareTo(p2.getCodice()));
            resultDto.setResult(lp);
        } else {
            // ... se il dto non esiste, restituisce un ArrayList vuoto
            resultDto.setResult(Collections.emptyList());
        }
        return resultDto;
    }
}
