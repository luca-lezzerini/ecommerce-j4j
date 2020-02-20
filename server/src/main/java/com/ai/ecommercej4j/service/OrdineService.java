package com.ai.ecommercej4j.service;

import com.ai.ecommercej4j.model.OrdineCreateDto;

public interface OrdineService {

    /**
     * Se l'utente non ha un ordine associato, gliene crea uno. Aggiunge al suo
     * ordine una prodotto, se esso è gia presente ne aggiorna la quantità
     *
     * @param dto Contiene il token dell'utente e il prodotto da aggiungere al
     * carrello
     */
    void addCarrello(OrdineCreateDto dto);

    void viewCarrello(OrdineCreateDto dto);
}
