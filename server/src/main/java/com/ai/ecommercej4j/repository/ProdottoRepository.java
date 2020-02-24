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
   
    Slice<Prodotto> findByCodiceOrDescrizioneContainingIgnoreCase(String key, String key2, Pageable pageable);

    Slice<Prodotto> findByPrezzoLessThanEqualAndOfferta(double prezzo, boolean offerta, Pageable pagina);

    Slice<Prodotto> findByOfferta(boolean offerta, Pageable pagina);
    
    Integer countByPrezzoLessThanEqualAndOfferta(double prezzo, boolean offerta);
    
    Integer countByDescrizioneContaining(String descrizione);
    
}
