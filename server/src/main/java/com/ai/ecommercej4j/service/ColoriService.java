
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
     * Cerca i colori salvati nel database, li impagina e li restituisce
     * Effettua la ricerca per codice e descrizione
     * @param dto contiene la chiave di ricerca e la pagina da visualizzare
     * @return una slice di elementi da visualizzare
     */
    ColoriSearchResultsDto searchColori(ColoriSearchDto dto);
    
    
    /**
     * Cancella il colore tramite l'id
     * @param dto contiene l'id dell'elemento da eliminare
     */
    void deleteColori(ColoriDeleteDto dto);
    
    /**
     * Cerca il colore tramite l'id, apporta le modifiche e lo salva in repository
     * @param dto contiene i parametri modificati di colore
     */
    void updateColori(ColoriUpdateDto dto);
}
