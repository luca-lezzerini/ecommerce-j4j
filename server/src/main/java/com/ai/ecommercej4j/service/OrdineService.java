package com.ai.ecommercej4j.service;

import com.ai.ecommercej4j.model.LoginResponseDto;
import com.ai.ecommercej4j.model.AggiungiCarrelloDto;
import com.ai.ecommercej4j.model.OrdineSearchDto;
import com.ai.ecommercej4j.model.OrdineSearchResultsDto;
import com.ai.ecommercej4j.model.ViewCarrelloResponseDto;

public interface OrdineService {

    /**
     * Se l'utente non ha un ordine associato, gliene crea uno. Aggiunge al suo
     * ordine una prodotto, se esso è gia presente ne aggiorna la quantità
     *
     * @param dto Contiene il token dell'utente e il prodotto da aggiungere al
     * carrello
     */
    void addCarrello(AggiungiCarrelloDto dto);

    /**
     * Recupera l'ordine dell'utente associato al token che riceve da
     * LoginResponseDto
     *
     * @param dto LoginResponseDto contente il token
     *
     * @return ViewCarrelloResponseDto contenente l'ordine le righe e il totale
     */
    ViewCarrelloResponseDto viewCarrello(LoginResponseDto dto);

    OrdineSearchResultsDto searchOrdineDaSpedire(OrdineSearchDto dto);

    /**
     * Cerca gli ordini dell'utente secondo le chiavi di ricerca inserite. Se
     * non trova nulla o se l'utente non è autenticato, restituisce una lista
     * vuota immodificabile.
     *
     * @param dto contiene il token dell'utente di cui vengono cercati gli
     * ordini, i parametri di ricerca: data, numero dell'ordine e stato, e il
     * numero di pagina richiesto
     *
     * @return dto con lista di ordini dell'utente, il numero di pagina
     * restitito e un boolean che descrive se la pagina restituita è l'ultima
     */
    OrdineSearchResultsDto searchOrdine(OrdineSearchDto dto);
}
