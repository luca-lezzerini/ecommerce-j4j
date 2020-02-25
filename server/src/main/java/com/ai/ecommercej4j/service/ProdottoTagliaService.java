/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.service;

import com.ai.ecommercej4j.model.ProdottoTagliaResponseDto;
import com.ai.ecommercej4j.model.Prodotto;
import com.ai.ecommercej4j.model.ProdottoTagliaRequestDto;

/**
 *
 * @author utente
 */
public interface ProdottoTagliaService {

    /**
     * Cerca la lista di taglia associate a quel prodotto
     *
     * @param prodotto Il prodotto di cui trovare le taglie
     * @return Un dto contenente due liste: una con le taglie disponibili e una
     * con le taglie non disponibili
     */
    ProdottoTagliaResponseDto getTaglie(Prodotto prodotto);

    /**
     * Aggiunge la taglia al prodotto selezionato
     *
     * @param dto Dto contenente il prodotto e la taglia da aggiungere
     * @return Un dto contenente due liste: una con le taglie disponibili e una
     * con le taglie non disponibili
     */
    ProdottoTagliaResponseDto addTaglia(ProdottoTagliaRequestDto dto);

    /**
     * Rimuove la taglia al prodotto selezionato
     *
     * @param dto Dto contenente il prodotto e la taglia da rimuovere
     * @return Un dto contenente due liste: una con le taglie disponibili e una
     * con le taglie non disponibili
     */
    ProdottoTagliaResponseDto removeTaglia(ProdottoTagliaRequestDto dto);
}
