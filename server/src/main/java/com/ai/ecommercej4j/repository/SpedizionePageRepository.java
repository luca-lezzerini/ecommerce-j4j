/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.repository;

import com.ai.ecommercej4j.model.Spedizione;
import java.awt.print.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

// creiamo repository spedizione con paginazione 
public interface SpedizionePageRepository extends PagingAndSortingRepository<Spedizione, Long> {
    
    //Page<Spedizione> findAll(Pageable pageable);
    
    Page <Spedizione> findByCodiceContainingIgnoreCase(String codice, Pageable pageable);
    
    Slice <Spedizione> findByPrezzoLessThan(Double prezzo, Pageable pageable);
    
    
}
