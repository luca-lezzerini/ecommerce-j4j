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
        return ps.createProdotto();
    }
    
    @RequestMapping(value = "/search-prodotto")
    @ResponseBody
    public ProdottoSearchResultsDto searchProdotto(@RequestBody ProdottoSearchDto dto) {
        return ps.searchProdotto();
    }
    
    @RequestMapping(value = "/delete-prodotto")
    @ResponseBody
    public void deleteProdotto(@RequestBody ProdottoDeleteDto dto) {
        return ps.deleteProdotto();
    }
    @RequestMapping(value = "/update-prodotto")
    @ResponseBody
    public void updateProdotto(@RequestBody ProdottoUpdateDto dto) {
        
        return ps.updateProdotto();
    }
}
