package com.ai.ecommercej4j.repository;

import com.ai.ecommercej4j.model.Taglia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagliaRepository extends JpaRepository<Taglia, Long> {

    Page<Taglia> findByCodiceOrDescrizioneContainingIgnoreCase(String codice, String descrizione, Pageable pageable);

    Taglia findByCodiceIgnoreCase(String codice);

    Page<Taglia> findByDescrizioneContainingIgnoreCase(String dto, Pageable pageable);

    Page<Taglia> findAll(Pageable pageable);
}
