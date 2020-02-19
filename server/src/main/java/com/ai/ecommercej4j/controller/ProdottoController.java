package com.ai.ecommercej4j.controller;

import com.ai.ecommercej4j.model.ProdottoCreateDto;
import com.ai.ecommercej4j.model.ProdottoDeleteDto;
import com.ai.ecommercej4j.model.ProdottoSearchDto;
import com.ai.ecommercej4j.model.ProdottoSearchResultsDto;
import com.ai.ecommercej4j.model.ProdottoUpdateDto;
import com.ai.ecommercej4j.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class ProdottoController {

    @Autowired
    ProdottoService ps;

    @RequestMapping(value = "/create-prodotto")
    @ResponseBody
    public void createProdotto(@RequestBody ProdottoCreateDto dto) {
        ps.createProdotto(dto);
    }

    @RequestMapping(value = "/search-prodotto")
    @ResponseBody
    public ProdottoSearchResultsDto searchProdotto(@RequestBody ProdottoSearchDto dto) {
        return ps.searchProdotto(dto);
    }
    
    @RequestMapping(value = "/search-prodotto-descrizione")
    @ResponseBody
    public ProdottoSearchResultsDto searchProdottoDescrizione(@RequestBody ProdottoSearchDto dto) {
        return ps.searchProdottoPerDescrizione(dto);
    }

    @RequestMapping(value = "/delete-prodotto")
    @ResponseBody
    public void deleteProdotto(@RequestBody ProdottoDeleteDto dto) {
        ps.deleteProdotto(dto);
    }

    @RequestMapping(value = "/update-prodotto")
    @ResponseBody
    public void updateProdotto(@RequestBody ProdottoUpdateDto dto) {
        ps.updateProdotto(dto);
    }
    
    @RequestMapping(value = "/search-offerte")
    @ResponseBody
    public ProdottoSearchResultsDto searchOfferte(@RequestBody ProdottoSearchDto dto) {
       return ps.searchOfferte(dto);
    }
}
