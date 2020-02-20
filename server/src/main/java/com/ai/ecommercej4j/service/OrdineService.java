package com.ai.ecommercej4j.service;

import com.ai.ecommercej4j.model.LoginResponseDto;
import com.ai.ecommercej4j.model.OrdineCreateDto;
import com.ai.ecommercej4j.model.ViewCarrelloResponseDto;

public interface OrdineService {
    
    void addCarrello(OrdineCreateDto dto);

    ViewCarrelloResponseDto viewCarrello(LoginResponseDto dto);
}
