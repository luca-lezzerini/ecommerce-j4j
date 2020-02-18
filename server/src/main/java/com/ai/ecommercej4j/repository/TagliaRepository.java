package com.ai.ecommercej4j.repository;

import com.ai.ecommercej4j.model.Taglia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagliaRepository extends JpaRepository<Taglia, Long> {

    List<Taglia> findByCodiceOrDescrizioneIgnoreCase(String codice, String descrizione);

    Taglia findByCodiceIgnoreCase(String codice);
    
    List<Taglia> findByDescrizioneContainingIgnoreCase(String dto);

}
