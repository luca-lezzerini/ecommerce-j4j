/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.repository;


import com.ai.ecommercej4j.model.Prodotto;
import com.ai.ecommercej4j.model.ProdottoTaglia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author utente
 */
@Repository
public interface ProdottoTagliaRepository extends JpaRepository<ProdottoTaglia, Long> {
    List<ProdottoTaglia> findByProdotto(Prodotto prodotto);
}
