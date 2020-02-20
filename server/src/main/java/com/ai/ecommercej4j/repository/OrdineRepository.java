package com.ai.ecommercej4j.repository;

import com.ai.ecommercej4j.model.Ordine;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrdineRepository extends JpaRepository<Ordine, Long> {

    @Query(value = "SELECT MAX(numero) FROM ordine", nativeQuery = true)
    Optional<Integer> selectMaxNumero();
}
