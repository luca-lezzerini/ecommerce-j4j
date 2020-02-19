package com.ai.ecommercej4j.service;

import com.ai.ecommercej4j.model.TagliaCreateDto;
import com.ai.ecommercej4j.model.TagliaDeleteDto;
import com.ai.ecommercej4j.model.TagliaSearchDto;
import com.ai.ecommercej4j.model.TagliaSearchResultsDto;
import com.ai.ecommercej4j.model.TagliaUpdateDto;

public interface TagliaService {

    /**
     * Verifica che il token sia esistente, per poter autorizzare l'utente.
     * Verifico se esiste già una taglia con lo stesso codice, nel caso non
     * esiste viene salvata nel db, se già esiste non succede nulla.
     *
     * @param dto rappresenta i dati necessari per la creazione della taglia.
     */
    void createTaglia(TagliaCreateDto dto);

    /**
     * Controlla che il token esista, se esiste verifica se la chiave di ricerca
     * è vuota. Se la chiave di ricerca è vuota vengono ritornate tutte le
     * taglie, altrimenti quelle che soddisfano la ricerca. Se il token non
     * esiste verrà ritornata una lista vuota
     *
     * @param dto rappresenta i dati necessari per poter effettuare la ricerca.
     * @return restituisce una lista di taglie.
     */
    TagliaSearchResultsDto searchTaglia(TagliaSearchDto dto);

    /**
     * Controlla che il token esista, se esiste verifica che la chiave di
     * ricerca è vuota. Se la chiave di ricerca è vuota vengono ritornate tutte
     * le taglie, altrimenti quelle che soddisfano la ricerca. Se il token non
     * esiste verrà ritornata una lista vuota
     *
     * @param dto rappresenta i dati necessari per poter effettuare la ricerca.
     * @return restituisce una lista di taglie.
     */
    TagliaSearchResultsDto searchTagliaPerDescrizione(TagliaSearchDto dto);

    /**
     * Controlla che il token esista, se esiste cancella la taglia nel db
     * tramite l'id.
     *
     * @param dto rappresenta i dati necessari per poter cancellare una taglia
     * nel db
     */
    void deleteTaglia(TagliaDeleteDto dto);

    /**
     * Controlla che il token esista, se esiste controlla che l'id sia diverso
     * da null. Se l'id esiste viene modificata la taglia sul db.
     *
     * @param dto rappresenta i dati necessari per poter modificare la taglia.
     */
    void updateTaglia(TagliaUpdateDto dto);

}
