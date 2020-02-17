package com.ai.ecommercej4j.repository;

import com.ai.ecommercej4j.model.Prodotto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long>{
    List<Prodotto> findByCodiceContainingIgnoreCase(String codice);
}
