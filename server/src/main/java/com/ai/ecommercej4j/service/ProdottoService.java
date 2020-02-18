package com.ai.ecommercej4j.service;

import com.ai.ecommercej4j.model.ProdottoCreateDto;
import com.ai.ecommercej4j.model.ProdottoDeleteDto;
import com.ai.ecommercej4j.model.ProdottoSearchDto;
import com.ai.ecommercej4j.model.ProdottoSearchResultsDto;
import com.ai.ecommercej4j.model.ProdottoUpdateDto;

public interface ProdottoService {

    void createProdotto(ProdottoCreateDto dto);

    ProdottoSearchResultsDto searchProdotto(ProdottoSearchDto dto);

    void deleteProdotto(ProdottoDeleteDto dto);

    void updateProdotto(ProdottoUpdateDto dto);
} 
