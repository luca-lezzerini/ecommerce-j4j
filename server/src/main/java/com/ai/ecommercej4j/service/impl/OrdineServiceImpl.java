package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.Ordine;
import com.ai.ecommercej4j.model.OrdineCreateDto;
import com.ai.ecommercej4j.model.OrdineSearchDto;
import com.ai.ecommercej4j.model.OrdineSearchResultsDto;
import com.ai.ecommercej4j.model.Prodotto;
import com.ai.ecommercej4j.model.RigaOrdine;
import com.ai.ecommercej4j.model.Utente;
import com.ai.ecommercej4j.repository.OrdineRepository;
import com.ai.ecommercej4j.repository.ProdottoRepository;
import com.ai.ecommercej4j.repository.RigaOrdineRepository;
import com.ai.ecommercej4j.repository.UtenteRepository;
import com.ai.ecommercej4j.service.OrdineService;
import com.ai.ecommercej4j.service.SecurityService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdineServiceImpl implements OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;

    @Autowired
    private RigaOrdineRepository rigaOrdineRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Override
    public void addCarrello(OrdineCreateDto dto) {
        String token = dto.getToken();
        Prodotto prodotto = dto.getDati();
        Utente utente = utenteRepository.findByToken(token);
        // Verifica se il token è di un utente anonimo o registsto...
        if (securityService.checkToken(token) || securityService.checkAnonimo(token)) {
            // ... se risuta positivo recupera l'ordine dell'utente nello stato carrello
            Optional<Ordine> optionalOrdine = utente.getOrdini().stream()
                    .filter(o -> o.getStato().equals("carrello"))
                    .findFirst();
            Ordine ordine;
            // Se l'utente non ha ordini nello stato carrello...
            if (optionalOrdine.isEmpty()) {
                // ...ne viene creato uno
                ordine = new Ordine(utente);
                ordineRepository.save(ordine);
                // Viene associato l'ordine all'utente
                utente.getOrdini().add(ordine);
                utenteRepository.save(utente);
            } else {
                // ...altrimenti viene recuperato l'ordine già esistente
                ordine = optionalOrdine.get();
            }
            // Recupera la riga del prodotto da aggiungere
            Optional<RigaOrdine> optionalRiga = ordine.getRighe().stream()
                    .filter(r -> r.getProdotto().getId().equals(prodotto.getId()))
                    .findFirst();
            RigaOrdine rigaOrdine;
            // Se la riga non esiste...
            if (optionalRiga.isEmpty()) {
                // ... ne crea una nuova con quantità uguale ad 1
                rigaOrdine = new RigaOrdine(1, ordine, prodotto);
                rigaOrdineRepository.save(rigaOrdine);
                // Associa la nuova riga all'ordine
                ordine.getRighe().add(rigaOrdine);
                ordineRepository.save(ordine);
                // Associa la riga al prodotto
                prodotto.getRighe().add(rigaOrdine);
                prodottoRepository.save(prodotto);
            } else {
                // ...altrimenti ne incrementa di 1 la quantità
                rigaOrdine = optionalRiga.get();
                rigaOrdine.setQta(rigaOrdine.getQta() + 1);
                rigaOrdineRepository.save(rigaOrdine);
            }
        }
    }

    @Override
    public void viewCarrello(OrdineCreateDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrdineSearchResultsDto searchOrdineDaSpedire(OrdineSearchDto dto) {

        // istanzio il dto di ritorno
        OrdineSearchResultsDto resultDto = new OrdineSearchResultsDto();
        List<Ordine> listaOrdine;
        // controllo se il dto e la chiave di ricerca sono diversi da null
        if (dto != null && dto.getSearchData() != null && dto.getSearchNumeroOrdine() != null) {

            //recupero i risultati e avvaloro il dto di ritorno                
            listaOrdine = ordineRepository.
                    findByDataAndNumeroContaining(dto.getSearchData(), dto.getSearchNumeroOrdine());

            // ordino i risultati per data
            Collections.sort(listaOrdine, (o1, o2) -> o1.getData().compareTo(o2.getData()));
            resultDto.setResults(listaOrdine);

        } else if (dto != null && dto.getSearchData() != null && dto.getSearchNumeroOrdine() == null) {
            listaOrdine = ordineRepository.
                    findByDataContaining(dto.getSearchData());

            // ordino i risultati per data
            Collections.sort(listaOrdine, (o1, o2) -> o1.getData().compareTo(o2.getData()));
            resultDto.setResults(listaOrdine);

        } else if (dto != null && dto.getSearchData() == null && dto.getSearchNumeroOrdine() != null) {
            listaOrdine = ordineRepository.
                    findByNumeroContaining(dto.getSearchNumeroOrdine());

            // ordino i risultati per data
            Collections.sort(listaOrdine, (o1, o2) -> o1.getData().compareTo(o2.getData()));
            resultDto.setResults(listaOrdine);

        } else if (dto != null && dto.getSearchData() == null && dto.getSearchNumeroOrdine() == null) {
            listaOrdine = ordineRepository.
                    findAll();

            // ordino i risultati per data
            Collections.sort(listaOrdine, (o1, o2) -> o1.getData().compareTo(o2.getData()));
            resultDto.setResults(listaOrdine);

        } else {
            // ... se il dto non esiste, restituisce un ArrayList vuoto
            resultDto.setResults(Collections.emptyList());
        }
        return resultDto;
    }
}
