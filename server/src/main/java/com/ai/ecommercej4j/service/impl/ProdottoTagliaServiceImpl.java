/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.ProdottoTagliaResponseDto;
import com.ai.ecommercej4j.model.Prodotto;
import com.ai.ecommercej4j.model.ProdottoTaglia;
import com.ai.ecommercej4j.model.ProdottoTagliaRequestDto;
import com.ai.ecommercej4j.model.Taglia;
import com.ai.ecommercej4j.repository.ProdottoTagliaRepository;
import com.ai.ecommercej4j.repository.TagliaRepository;
import com.ai.ecommercej4j.service.ProdottoTagliaService;
import java.util.ArrayList;
import java.util.Collections;
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
        return trovaTaglie(prodotto);
    }

    @Override
    public ProdottoTagliaResponseDto addTaglia(ProdottoTagliaRequestDto dto) {
        List<ProdottoTaglia> listaPt = new ArrayList<>();
        ProdottoTaglia pt = new ProdottoTaglia();
        pt.setProdotto(dto.getProdotto());
        // per ogni taglia presente nella lista del dto aggiungo un elemento alla lista di ProdottoTaglie che deve essere aggiunta 
        for (Taglia t : dto.getTaglia()) {
            pt.setTaglia(t);
            listaPt.add(pt);
        }
        prodottoTagliaRepository.saveAll(listaPt);
        return trovaTaglie(pt.getProdotto());
    }

    @Override
    public ProdottoTagliaResponseDto removeTaglia(ProdottoTagliaRequestDto dto) {
        List<ProdottoTaglia> listaPt = new ArrayList<>();
        ProdottoTaglia pt = new ProdottoTaglia();
        // per ogni taglia presente nella lista del dto aggiungo un elemento alla lista di ProdottoTaglie che deve essere eliminata 
        for (Taglia t : dto.getTaglia()) {
            pt = prodottoTagliaRepository.findByProdottoAndTaglia(dto.getProdotto(), t);
            listaPt.add(pt);
        }
        prodottoTagliaRepository.deleteAll(listaPt);
        return trovaTaglie(pt.getProdotto());
    }

    private ProdottoTagliaResponseDto trovaTaglie(Prodotto prodotto) {
        List<ProdottoTaglia> lista = prodottoTagliaRepository.findByProdotto(prodotto);
        List<Taglia> taglieNonDisponibili = new ArrayList<>();
        List<Taglia> taglieDisponibili = new ArrayList<>();
        // controllo che la lista di taglie disponibili non sia null
        // se è null tagleDisponibili sarà una lista vuota, taglieNonDisponibili conterrà tutte le taglie...
        if (lista == null) {
            taglieDisponibili = Collections.emptyList();
            taglieNonDisponibili = tagliaRepository.findAll();
            //... altrimenti aggiunge a taglieDisponibili le taglie assegnate al prodotto e le rimuove da TaglieNonDisponibili
        } else {
            taglieNonDisponibili = tagliaRepository.findAll();
            for (ProdottoTaglia pt : lista) {
                taglieDisponibili.add(pt.getTaglia());
                taglieNonDisponibili.remove(pt.getTaglia());
            }
        }
        ProdottoTagliaResponseDto rdto = new ProdottoTagliaResponseDto(taglieDisponibili, taglieNonDisponibili);
        System.out.println("\n"+taglieDisponibili+"\n");
        System.out.println("\n"+taglieNonDisponibili+"\n");
        return rdto;
    }
}
