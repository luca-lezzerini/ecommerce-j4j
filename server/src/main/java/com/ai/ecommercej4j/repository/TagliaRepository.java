package com.ai.ecommercej4j.repository;

import com.ai.ecommercej4j.model.Taglia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagliaRepository extends JpaRepository<Taglia, Long>{
    Taglia findByCodiceOrDescrizione(String filtro); 
    Taglia findByCodice(String codice);
    
}
