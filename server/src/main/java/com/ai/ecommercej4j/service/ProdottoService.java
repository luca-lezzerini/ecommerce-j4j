package com.ai.ecommercej4j.service;

import com.ai.ecommercej4j.model.ProdottoCreateDto;
import com.ai.ecommercej4j.model.ProdottoDeleteDto;
import com.ai.ecommercej4j.model.ProdottoSearchDto;
import com.ai.ecommercej4j.model.ProdottoSearchResultsDto;
import com.ai.ecommercej4j.model.ProdottoUpdateDto;

public interface ProdottoService {

    /**
     * inserisce un nuovo Prodotto. Non viene inserito nei casi in cui: il
     * parametro dto è null; la proprietà dati del dto è null; il codice del
     * prodotto da inserire è una stringa vuota o null; il token non è valido;
     * il codice del prodotto da inserire esiste già. Non genera eccezioni in
     * questi casi
     *
     * @param dto contiene il token e il prodotto da inserire
     */
    void createProdotto(ProdottoCreateDto dto);

    /**
     * ricerca i prodotti in base al valore della proprietà searchKey di dto e
     * il cui numero di pagina è specificato nella proprietà numeroPagina del
     * dto. Restituisce un dto la cui proprietà results contiene un ArrayList di
     * massimo 5 prodotti, nel cui codice è contenuta la searchKey. Se la pagina
     * da cercare è un numero negativo o superiore a quello delle pagine totali,
     * restituisce l'ultima pagina. Restituisce una lista vuota immodificabile
     * se il parametro dto è null, o se non trova risultati, o se il token non è
     * valido.
     *
     * @param dto contiene il token, la chiave di ricerca e il numero di pagina.
     *
     * @return ProdottoSearchResultsDto con i parametri: result, contenente un
     * ArrayList con i risultati o una lista vuota immodificabile se non ci sono
     * risultati; numeroPagina il numero di pagina restituita; ultimaPagina di
     * tipo boolean, uguale a true se la pagina restituita è l'ultima, false
     * altrimenti
     */
    ProdottoSearchResultsDto searchProdotto(ProdottoSearchDto dto);

    ProdottoSearchResultsDto searchProdottoRiservato(ProdottoSearchDto dto);

    /**
     * ricerca Prodotto in base al valore della proprietà searchKey di dto.
     * Restituisce un dto la cui proprietà results contiene un ArrayList di
     * Prodotto, il cui codice contiene la searchKey. Restituisce una lista
     * vuota immodificabile se il parametro dto è null, o se non trova
     * risultati, o se il token non è valido.
     *
     * @param dto contiene il token e la chiave di ricerca
     * @return ArrayList<Prodotto>
     */
    ProdottoSearchResultsDto searchProdottoPerDescrizione(ProdottoSearchDto dto);

    /**
     * elimina un Prodotto avente id uguale alla proprietà idToDelete del dto.
     * Non elimina nulla se il parametro dto è null, o se il token non è valido,
     * o se la proprietà idToDelete del dto è null. Non genera eccezioni in
     * questi casi.
     *
     * @param dto contiene il token e l'id del prodotto da eliminare
     */
    void deleteProdotto(ProdottoDeleteDto dto);

    /**
     * modifica i valori di un Prodotto corrispondente che ha id uguale a
     * dto.dati.id. Non esegue la modifica se il dto o dto.dati sono null, o se
     * dto.codice è null o stringa vuota, o se il token non è valido, o se
     * dto.dati.id è una stringa vuota. Non genera eccezioni in questi casi.
     *
     * @param dto contiene il token e il prodotto da modificare
     */
    void updateProdotto(ProdottoUpdateDto dto);

    /**
     * Ricerca prodotti in offerta con prezzo minore a quello inserito.
     * Restituisce un dto la cui proprietà results contiene un ArrayList di
     * Prodotto, il cui codice contiene la searchKey. Restituisce una lista
     * vuota immodificabile se il parametro dto è null, o se non trova
     * risultati, o se il token non è valido.
     *
     * @param dto contiene il token e la chiave di ricerca (il prezzo)
     * @return ArrayList<Prodotto>
     *
     */
    ProdottoSearchResultsDto searchOfferte(ProdottoSearchDto dto);
}
