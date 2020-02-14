package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.Prodotto;
import com.ai.ecommercej4j.model.ProdottoCreateDto;
import com.ai.ecommercej4j.model.ProdottoDeleteDto;
import com.ai.ecommercej4j.model.ProdottoSearchDto;
import com.ai.ecommercej4j.model.ProdottoSearchResultsDto;
import com.ai.ecommercej4j.model.ProdottoUpdateDto;
import com.ai.ecommercej4j.service.ProdottoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProdottoServiceImpl implements ProdottoService{

    private List<Prodotto> prodotti = new ArrayList<>();
    
    @Override
    public void createProdotto(ProdottoCreateDto dto) {
    }

    @Override
    public ProdottoSearchResultsDto searchProdotto(ProdottoSearchDto dto) {
        return new ProdottoSearchResultsDto();
    }

    @Override
    public void deleteProdotto(ProdottoDeleteDto dto) {
        
    }

    @Override
    public void updateProdotto(ProdottoUpdateDto dto) {
        
    }
    
}
