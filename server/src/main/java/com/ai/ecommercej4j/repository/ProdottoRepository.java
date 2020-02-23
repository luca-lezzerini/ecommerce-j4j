package com.ai.ecommercej4j.repository;

import com.ai.ecommercej4j.model.Prodotto;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {

    List<Prodotto> findByCodiceContainingIgnoreCase(String codice);
    
    List<Prodotto> findByCodiceIgnoreCase(String codice);
    
    Integer countByCodiceContaining(String codice);
    
    Slice<Prodotto> findByCodiceContainingIgnoreCase(String codice, Pageable page);
   
    List<Prodotto> findByDescrizioneContainingIgnoreCase(String descrizione);

    List<Prodotto> findByPrezzoLessThanEqualAndOfferta(double prezzo, boolean offerta);

    List<Prodotto> findByOfferta(boolean offerta);
    
    
}
