/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.service;

import com.ai.ecommercej4j.model.ProdottoTagliaResponseDto;
import com.ai.ecommercej4j.model.Prodotto;

/**
 *
 * @author utente
 */
public interface ProdottoTagliaService {
    
    ProdottoTagliaResponseDto getTaglie(Prodotto prodotto);
}
