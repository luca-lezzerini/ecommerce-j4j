package com.ai.ecommercej4j.service;

import com.ai.ecommercej4j.model.TagliaCreateDto;
import com.ai.ecommercej4j.model.TagliaDeleteDto;
import com.ai.ecommercej4j.model.TagliaSearchDto;
import com.ai.ecommercej4j.model.TagliaSearchResultsDto;
import com.ai.ecommercej4j.model.TagliaUpdateDto;

public interface TagliaService {

    void createTaglia(TagliaCreateDto dto);
    /**
     * Verifica che il token sia esistente, per poter 
     * autorizzare l'utente.
     * Verifico se esiste già una taglia con lo stesso codice,
     * nel caso non esiste viene salvata nel db,
     * se già esiste non succede nulla.       
     * @param dto rappresenta i dati necessari per la creazione della taglia.
     * @return non restituisce nulla dato che il risultato verrà salvato nel db.
     */

    TagliaSearchResultsDto searchTaglia(TagliaSearchDto dto);
    /**
     * Crea un oggetto result di tipo TagliaSearchResultsDto, dove 
     * verranno messi i risultati della ricerca.
     * Crea una varibaile ricerca dove verrà inserita la chiave di 
     * ricerca.
     * Controlla che il token esista, se esiste verifica che la chiave di
     * ricerca è vuota.
     * Se la chiave di ricerca è vuota vengono ritornate tutte le taglie,
     * altrimenti quelle che soddisfano la ricerca.
     * Se il token non esiste verrà ritornata una lista vuota
     * @param dto rappresenta i dati necessari per poter effettuare la 
     * ricerca.
     * @return restituisce un result di tipo TagliaSearchResultsDto.
     */
    
    TagliaSearchResultsDto searchTagliaPerDescrizione(TagliaSearchDto dto);
    /**
     * Crea un oggetto result di tipo TagliaSearchResultsDto, dove 
     * verranno messi i risultati della ricerca.
     * Crea una varibaile ricerca dove verrà inserita la chiave di 
     * ricerca.
     * Controlla che il token esista, se esiste verifica che la chiave di
     * ricerca è vuota.
     * Se la chiave di ricerca è vuota vengono ritornate tutte le taglie,
     * altrimenti quelle che soddisfano la ricerca.
     * Se il token non esiste verrà ritornata una lista vuota
     * @param dto rappresenta i dati necessari per poter effettuare la 
     * ricerca.
     * @return restituisce un result di tipo TagliaSearchResultsDto.
     */
    
    void deleteTaglia(TagliaDeleteDto dto);
    /**
     * Controlla che il token esista, se esiste cancella la taglia
     * nel db tramite l'id.
     * @param dto rappresenta i dati necessari per poter cancellare
     * una taglia nel db
     * @return non restituisce nulla dato che deve fare solo 
     * operazioni di cancellazione.
     */

    void updateTaglia(TagliaUpdateDto dto);
    /**
     * Controlla che il token esista, se esiste controlla che l'id
     * sia diverso da null.
     * Se l'id esiste viene modificata la taglia sul db.
     * @param dto rappresenta i dati necessari per poter modificare
     * la taglia.
     * @return non restituisce nulla dato che modifica le taglie
     * direttamente sul db.
     */
}
