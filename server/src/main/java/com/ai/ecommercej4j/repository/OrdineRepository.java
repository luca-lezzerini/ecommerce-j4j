package com.ai.ecommercej4j.repository;

import com.ai.ecommercej4j.model.Ordine;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdineRepository 
        extends JpaRepository<Ordine, Long>{

    List<Ordine> findByDataContaining(LocalDate data);
    
    List<Ordine> findByNumeroContaining(String numero);
    
    List<Ordine> findByDataAndNumeroContaining(LocalDate data, String numero);
}
