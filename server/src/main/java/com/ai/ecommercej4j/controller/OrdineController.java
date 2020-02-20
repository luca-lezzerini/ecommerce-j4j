package com.ai.ecommercej4j.controller;

import com.ai.ecommercej4j.model.OrdineCreateDto;
import com.ai.ecommercej4j.model.OrdineSearchDto;
import com.ai.ecommercej4j.model.OrdineSearchResultsDto;
import com.ai.ecommercej4j.service.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class OrdineController {
    
    @Autowired
    OrdineService ordineService;
    
    @RequestMapping("/add-carrello")
    @ResponseBody
    public void addCarrello(@RequestBody OrdineCreateDto dto){
        ordineService.addCarrello(dto);
    }
    
    @RequestMapping(value = "/search-ordini-da-spedire")
    @ResponseBody
    public OrdineSearchResultsDto searchOrdine(@RequestBody OrdineSearchDto dto) {
        return ordineService.searchOrdineDaSpedire(dto);
    }
}
