package com.ai.ecommercej4j.repository;

import com.ai.ecommercej4j.model.Colori;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColoriRepository extends JpaRepository<Colori, Long> {

    List<Colori> findByCodiceContainingIgnoreCase(String dto);

    List<Colori> findByDescrizioneContainingIgnoreCase(String dto, Pageable pageable);

    List<Colori> findByDescrizioneContainingIgnoreCase(String key);

}
