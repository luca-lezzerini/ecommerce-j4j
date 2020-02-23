package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.LoginResponseDto;
import com.ai.ecommercej4j.model.Ordine;
import com.ai.ecommercej4j.model.AggiungiCarrelloDto;
import com.ai.ecommercej4j.model.OrdineSearchDto;
import com.ai.ecommercej4j.model.OrdineSearchResultsDto;
import com.ai.ecommercej4j.model.Prodotto;
import com.ai.ecommercej4j.model.RigaOrdine;
import com.ai.ecommercej4j.model.Utente;
import com.ai.ecommercej4j.model.ViewCarrelloResponseDto;
import com.ai.ecommercej4j.repository.OrdineRepository;
import com.ai.ecommercej4j.repository.ProdottoRepository;
import com.ai.ecommercej4j.repository.RigaOrdineRepository;
import com.ai.ecommercej4j.repository.UtenteRepository;
import com.ai.ecommercej4j.service.OrdineService;
import com.ai.ecommercej4j.service.SecurityService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
    public void addCarrello(AggiungiCarrelloDto dto) {
        String token = dto.getToken();
        Prodotto prodotto = dto.getDati();
        Utente utente = utenteRepository.findByToken(token);
        // Verifica se il token è di un utente anonimo o registrato...
        if (securityService.checkToken(token) || securityService.checkAnonimo(token)) {
            // ... se vero recupera l'ordine dell'utente nello stato carrello
            Optional<Ordine> optionalOrdine = utente.getOrdini().stream()
                    .filter(o -> o.getStato().equals("carrello"))
                    .findFirst();
            Ordine ordine;
            // Se l'utente non ha ordini nello stato carrello...
            if (optionalOrdine.isEmpty()) {
                // ...ne viene creato uno
                ordine = new Ordine(utente, generateNumeroOrdine());
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
                    .filter(r -> r.getProdotto()
                    .getId().equals(prodotto.getId()))
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

    /**
     * Restituisce un numero di ordine auto incrementato
     *
     * @return il numero di ordine
     */
    private int generateNumeroOrdine() {
        return ordineRepository.selectMaxNumero().map(n -> n + 1).orElse(1);
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
                    findByDataAndNumero(dto.getSearchData(), dto.getSearchNumeroOrdine());

            // ordino i risultati per data
            Collections.sort(listaOrdine, (o1, o2) -> o1.getData().compareTo(o2.getData()));
            resultDto.setResults(listaOrdine);

        } else if (dto != null && dto.getSearchData() != null && dto.getSearchNumeroOrdine() == null) {
            listaOrdine = ordineRepository.
                    findByData(dto.getSearchData());

            // ordino i risultati per data
            Collections.sort(listaOrdine, (o1, o2) -> o1.getData().compareTo(o2.getData()));
            resultDto.setResults(listaOrdine);

        } else if (dto != null && dto.getSearchData() == null && dto.getSearchNumeroOrdine() != null) {
            listaOrdine = ordineRepository.
                    findByNumero(dto.getSearchNumeroOrdine());

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

    public ViewCarrelloResponseDto viewCarrello(LoginResponseDto dto) {
        ViewCarrelloResponseDto rdto = new ViewCarrelloResponseDto();
        String tok = dto.getToken();
        Ordine carrello = new Ordine();
        double totale = 0;

        // Recupero il token dall'utente con la utenteRepository
        Utente utente = utenteRepository.findByToken(tok);
        List<RigaOrdine> listaRigheOrdine;

        // Verifica se il token è di un utente anonimo o registrato...
        if (securityService.checkToken(tok) || securityService.checkAnonimo(tok)) {

            // ...recupera l'ordine con lo stato carrello...
            carrello = utente.getOrdini()
                    .parallelStream()
                    .filter(o -> o.getStato().equals("carrello"))
                    .findFirst()
                    .get();
            // ...recupera l'array con le righe dell'ordine...
            listaRigheOrdine = carrello.getRighe();

            // ...e per ogni riga dell'array calcola il totale del prezzo dei prodotti
            for (RigaOrdine r : listaRigheOrdine) {
                totale += (r.getProdotto().getPrezzo()*r.getQta());
            }
        } else {
            // Altrimenti restituisco una lista vuota
            listaRigheOrdine = Collections.emptyList();
        }
        // Setto i campi del dto e lo restituisco al client
        rdto.setCarrello(listaRigheOrdine);
        rdto.setOrdine(carrello);
        rdto.setTotale(totale);
        return rdto;
    }

    @Override
    public OrdineSearchResultsDto searchOrdine(OrdineSearchDto dto) {
        // istanzio il dto di ritorno
        OrdineSearchResultsDto resultDto = new OrdineSearchResultsDto();

        // controllo se il dto e lo stato sono diversi da null
        // creo la lista dei risultati
        List<Ordine> ordini = new ArrayList<>();
        if (dto != null && dto.getStato() != null) {
            if (securityService.checkToken(dto.getToken())) {
                //recupero i risultati e avvaloro il dto di ritorno 
                //tutte le ricerce sono effettuate per stato
                //effettuo una ricerca per data, numero e stato
                if (dto.getSearchData() != null && dto.getSearchNumeroOrdine() != null) {
                    ordini = ordineRepository.findByDataAndNumeroAndStato(dto.getSearchData(),dto.getSearchNumeroOrdine(),dto.getStato());
                } else if (dto.getSearchData() == null && dto.getSearchNumeroOrdine() != null) {
                    //effettuo una ricerca per numero e stato
                    ordini = ordineRepository.findByNumeroAndStatoContainingIgnoreCase(dto.getSearchNumeroOrdine(),dto.getStato());
                } else if (dto.getSearchData() != null && dto.getSearchNumeroOrdine() == null) {
                    //effettuo una ricerca per data e stato
                    ordini = ordineRepository.findByDataAndStato(dto.getSearchData(),dto.getStato());
                } else {
                    //effettuo una ricerca per stato
                    ordini = ordineRepository.findByStatoContainingIgnoreCase(dto.getStato());
                }
                // ordino i risultati per data discendente
                ordini = ordini.stream().sorted((o1, o2) -> o2.getData().
                        compareTo(o1.getData())).
                        collect(Collectors.toList());
            } else {
                // ... se il dto non esiste, restituisce un ArrayList vuoto
                ordini = Collections.emptyList();
            }
        }
        resultDto.setResults(ordini);
        return resultDto;
    }
}
