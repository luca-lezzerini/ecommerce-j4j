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
import org.springframework.stereotype.Service;

@Service
public class ProdottoServiceImpl implements ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Autowired
    private SecurityService securityService;

    /**
     * inserisce un nuovo Prodotto non viene inserito nei casi in cui: il
     * parametro dto è null; la proprietà dati del dto è null; il codice del
     * prodotto da inserire è una stringa vuota o null; il token non è valido;
     * il codice del prodotto da inserire esiste già
     *
     * @param dto contiene il token e il prodotto da inserire
     */
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
     * Prodotto, il cui codice contiene la searchKey restituisce una lista vuota
     * se il parametro dto è null, o se non trova risultati, o se il token non è
     * valido
     *
     * @param dto contiene il token e la chiave di ricerca
     * @return ArrayList<Prodotto>
     */
    @Override
    public ProdottoSearchResultsDto searchProdotto(ProdottoSearchDto dto) {

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

    /**
     * elimina un Prodotto avente id uguale alla proprietà idToDelete del dto.
     * non elimina nulla se il parametro dto è null, o se il token non è valido,
     * o se la proprietà idToDelete del dto è null
     *
     * @param dto contiene il token e l'id del prodotto da eliminare
     */
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

    /**
     * modifica i valori di un Prodotto corrispondente che ha id uguale a
     * dto.dati.id non esegue la modifica se il dto o dto.dati sono null, o se
     * dto.codice è null o stringa vuota, o se il token non è valido, o se
     * dto.dati.id è una stringa vuota
     *
     * @param dto contiene il token e il prodotto da modificare
     */
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

    /**
     * Ricerca prodotti in offerta con prezzo minore a quello inserito
     * restituisce un dto la cui proprietà results contiene un ArrayList di
     * Prodotto, il cui codice contiene la searchKey restituisce una lista vuota
     * se il parametro dto è null, o se non trova risultati, o se il token non è
     * valido
     *
     * @param dto contiene il token e la chiave di ricerca (il prezzo)
     * @return ArrayList<Prodotto>
     * */
    
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
            if ((dto.getSearchKey().trim()).equals("")) {
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
}
