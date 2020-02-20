package com.ai.ecommercej4j.repository;

import com.ai.ecommercej4j.model.Ordine;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrdineRepository extends JpaRepository<Ordine, Long> {

    @Query(value = "SELECT MAX(numero) FROM ordine", nativeQuery = true)
    Optional<Integer> selectMaxNumero();

    List<Ordine> findByDataContaining(LocalDate data);

    List<Ordine> findByNumeroContaining(Integer numero);

    List<Ordine> findByDataAndNumeroContaining(LocalDate data, Integer numero);
    
    List<Ordine> findByDataAndNumeroAndStato(LocalDate data, Integer numero, String stato);
    
    List<Ordine> findByDataAndStato(LocalDate data, String stato);
    
    List<Ordine> findByNumeroAndStatoContainingIgnoreCase(Integer numero, String stato);
    
    List<Ordine> findByStatoContainingIgnoreCase(String stato);
}
