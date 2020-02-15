package com.ai.ecommercej4j.repository;

import com.ai.ecommercej4j.model.Taglia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagliaRepository extends JpaRepository<Taglia, Long> {

    List<Taglia> findByCodiceOrDescrizione(String codice, String descrizione);

    Taglia findByCodice(String codice);

}
