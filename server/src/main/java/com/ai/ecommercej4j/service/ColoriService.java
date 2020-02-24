
package com.ai.ecommercej4j.service;

import com.ai.ecommercej4j.model.ColoriCreateDto;
import com.ai.ecommercej4j.model.ColoriDeleteDto;
import com.ai.ecommercej4j.model.ColoriSearchDto;
import com.ai.ecommercej4j.model.ColoriSearchResultsDto;
import com.ai.ecommercej4j.model.ColoriUpdateDto;


public interface ColoriService {
    
    /**
     * Crea un nuovo elemento colore e lo salva in repository 
     * @param dto che contiene i parametri di colore.
     */
    void createColori(ColoriCreateDto dto);
    
    /**
     * Cerca i colori salvati nella repository e li salva in una lista 
     * Effettua la ricerca per codice e descrizione
     * @param dto contiene il parametro su cui viene effettuata la ricerca
     * @return una lista di risultati
     */
    ColoriSearchResultsDto searchColori(ColoriSearchDto dto);
    
    
    /**
     * cancella il colore tramite l'id
     * @param dto contiene l'id dell'elemento da eliminare
     */
    void deleteColori(ColoriDeleteDto dto);
    
    /**
     * Cerca il colore tramite l'id, apporta le modifiche e lo salva in repository
     * @param dto contiene i parametri modificati di colore
     */
    void updateColori(ColoriUpdateDto dto);

    /**
     * ricerca i colori in base al valore della proprietà searchKey di dto e
     * il cui numero di pagina è specificato nella proprietà numeroPagina del
     * dto. Restituisce un dto la cui proprietà results contiene un ArrayList di
     * massimo 5 colori, nel cui codice è contenuta la searchKey. Se la pagina
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
    public ColoriSearchResultsDto searchColoriPerDescrizione(ColoriSearchDto dto);
}
