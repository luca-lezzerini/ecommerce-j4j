/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.repository;

import com.ai.ecommercej4j.model.Spedizione;

/**
 *
 * @author utente
 */
public interface SpedizioneRepository {
     public Spedizione findByeIdAndCodice(String id, String cod);
    
}
