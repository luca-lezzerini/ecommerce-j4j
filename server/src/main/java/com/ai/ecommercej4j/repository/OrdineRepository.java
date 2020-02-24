package com.ai.ecommercej4j.repository;

import com.ai.ecommercej4j.model.Ordine;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrdineRepository extends JpaRepository<Ordine, Long> {

    @Query(value = "SELECT MAX(numero) FROM ordine", nativeQuery = true)
    Optional<Integer> selectMaxNumero();

    Page<Ordine> findByData(LocalDate data, Pageable pageable);

    Page<Ordine> findByNumero(Integer numero, Pageable pageable);

    Page<Ordine> findByDataAndNumero(LocalDate data, Integer numero, Pageable pageable);

    Page<Ordine> findByDataAndNumeroAndStato(LocalDate data, Integer numero, String stato, Pageable pageable);

    Page<Ordine> findByDataAndStato(LocalDate data, String stato, Pageable pageable);

    Page<Ordine> findByNumeroAndStatoContainingIgnoreCase(Integer numero, String stato,Pageable pageable);

    Page<Ordine> findByStatoContainingIgnoreCase( String stato, Pageable pageable);

    Integer countByDataAndNumeroAndStato(LocalDate searchData, Integer searchNumeroOrdine, String stato);

    Integer countByNumeroAndStato(Integer searchNumeroOrdine, String stato);

    Integer countByDataAndStato(LocalDate searchData, String stato);

    Integer countByStato(String stato);

    public int countByDataAndNumero(LocalDate searchData, Integer searchNumeroOrdine);

    public int countByNumero(Integer searchNumeroOrdine);

    public int countByData(LocalDate searchData);
}
