/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.repository;

import com.ai.ecommercej4j.model.Spedizione;
import java.awt.print.Pageable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

// creo l'interfaccia del repository spedizioni
public interface SpedizioneRepository extends JpaRepository<Spedizione, Long> {

    List<Spedizione> findByCodiceContainingIgnoreCase(String codice);

    List<Spedizione> findByPrezzoLessThan(Double prezzo);
    
}
