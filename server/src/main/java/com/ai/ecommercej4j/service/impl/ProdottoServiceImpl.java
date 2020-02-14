package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.Prodotto;
import com.ai.ecommercej4j.model.ProdottoCreateDto;
import com.ai.ecommercej4j.model.ProdottoDeleteDto;
import com.ai.ecommercej4j.model.ProdottoSearchDto;
import com.ai.ecommercej4j.model.ProdottoSearchResultsDto;
import com.ai.ecommercej4j.model.ProdottoUpdateDto;
import com.ai.ecommercej4j.repository.ProdottoRepository;
import com.ai.ecommercej4j.service.ProdottoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdottoServiceImpl implements ProdottoService{

    @Autowired
    private ProdottoRepository prodottoRepository;
    
    @Override
    public void createProdotto(ProdottoCreateDto dto) {
    }

    @Override
    public ProdottoSearchResultsDto searchProdotto(ProdottoSearchDto dto) {
        // FIXME: codice stub
        List<Prodotto> lp = new ArrayList<>();
//        Prodotto p1 = new Prodotto(null, "P1", "P1descr", 100);
//        lp.add(p1);
//         p1 = new Prodotto(null, "P2", "P2descr", 200);
//        lp.add(p1);
//        p1 = new Prodotto(null, "P3", "P3descr", 300);
//        lp.add(p1);
        ProdottoSearchResultsDto pr = new ProdottoSearchResultsDto();
        pr.setResults(lp);
        return pr;
    }

    @Override
    public void deleteProdotto(ProdottoDeleteDto dto) {
        
    }

    @Override
    public void updateProdotto(ProdottoUpdateDto dto) {
        
    }
    
}
