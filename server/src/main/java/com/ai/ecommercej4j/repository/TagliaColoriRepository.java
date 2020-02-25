package com.ai.ecommercej4j.repository;

import com.ai.ecommercej4j.model.TagliaColori;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagliaColoriRepository extends JpaRepository<TagliaColori, Long> {

    List<TagliaColori> findByProdottoTagliaId(Long idProdottoTaglia);
}
