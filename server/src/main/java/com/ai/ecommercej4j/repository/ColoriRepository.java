package com.ai.ecommercej4j.repository;

import com.ai.ecommercej4j.model.Colori;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColoriRepository extends JpaRepository<Colori, Long> {

    List<Colori> findByCodiceContainingIgnoreCase(String key);
    
    Integer countByDescrizioneContaining(String descrizione);

    List<Colori> findByDescrizioneContainingIgnoreCase(String key);
    
    Slice<Colori> findByCodiceOrDescrizioneContainingIgnoreCase(String key, String key2, Pageable pageable);
    
}
