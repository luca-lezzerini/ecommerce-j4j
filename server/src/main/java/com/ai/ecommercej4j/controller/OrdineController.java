package com.ai.ecommercej4j.controller;

import com.ai.ecommercej4j.model.LoginResponseDto;
import com.ai.ecommercej4j.model.AggiungiCarrelloDto;
import com.ai.ecommercej4j.model.OrdineSearchDto;
import com.ai.ecommercej4j.model.OrdineSearchResultsDto;
import com.ai.ecommercej4j.model.ViewCarrelloResponseDto;
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
    public void addCarrello(@RequestBody AggiungiCarrelloDto dto){
        ordineService.addCarrello(dto);
    }
    
    @RequestMapping(value = "/search-ordini-da-spedire")
    @ResponseBody
    public OrdineSearchResultsDto searchOrdiniDaSpedire(@RequestBody OrdineSearchDto dto) {
        return ordineService.searchOrdineDaSpedire(dto);
    }
    @RequestMapping("/view-carrello")
    @ResponseBody
    public ViewCarrelloResponseDto viewCarrello(@RequestBody LoginResponseDto dto){
        return ordineService.viewCarrello(dto);
    }
    
    @RequestMapping
    @ResponseBody
    public OrdineSearchResultsDto searchOrdine(@RequestBody OrdineSearchDto dto){
     return ordineService.searchOrdine(dto);   
    }
}
