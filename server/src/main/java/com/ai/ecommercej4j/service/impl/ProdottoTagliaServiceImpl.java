/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.ProdottoTagliaResponseDto;
import com.ai.ecommercej4j.model.Prodotto;
import com.ai.ecommercej4j.model.ProdottoTaglia;
import com.ai.ecommercej4j.model.Taglia;
import com.ai.ecommercej4j.repository.ProdottoTagliaRepository;
import com.ai.ecommercej4j.repository.TagliaRepository;
import com.ai.ecommercej4j.service.ProdottoTagliaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

/**
 *
 * @author utente
 */
@Service
public class ProdottoTagliaServiceImpl implements ProdottoTagliaService {

    @Autowired
    private ProdottoTagliaRepository prodottoTagliaRepository;

    @Autowired
    private TagliaRepository tagliaRepository;

    @Override
    public ProdottoTagliaResponseDto getTaglie(Prodotto prodotto) {
        List<ProdottoTaglia> lista = prodottoTagliaRepository.findByProdotto(prodotto);
        List<Taglia> taglieDisponibili = new ArrayList<>();
        List<Taglia> taglieNonDisponibili = tagliaRepository.findAll();
        for (ProdottoTaglia pt : lista) {
            taglieDisponibili.add(pt.getTaglia());
            taglieNonDisponibili.remove(pt.getTaglia());
        }
        ProdottoTagliaResponseDto rdto = new ProdottoTagliaResponseDto(taglieDisponibili, taglieNonDisponibili);
        return rdto;
    }
}
