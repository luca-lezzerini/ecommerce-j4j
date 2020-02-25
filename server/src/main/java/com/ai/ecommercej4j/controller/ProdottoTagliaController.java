/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.controller;


import com.ai.ecommercej4j.model.ProdottoTagliaResponseDto;
import com.ai.ecommercej4j.model.Prodotto;
import com.ai.ecommercej4j.service.ProdottoTagliaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
public class ProdottoTagliaController {
        
    @Autowired
    ProdottoTagliaService prodottoTagliaService;
    
    @RequestMapping(value = "/get-taglie")
    @ResponseBody
    public ProdottoTagliaResponseDto getTaglie(@RequestBody Prodotto prodotto) {
        return prodottoTagliaService.getTaglie(prodotto);
    }
    @RequestMapping(value = "/add-taglia")
    @ResponseBody
    public ProdottoTagliaResponseDto addTaglia(@RequestBody Prodotto prodotto) {
        return prodottoTagliaService.getTaglie(prodotto);
    }
    @RequestMapping(value = "/remove-taglia")
    @ResponseBody
    public ProdottoTagliaResponseDto removeTaglia(@RequestBody Prodotto prodotto) {
        return prodottoTagliaService.getTaglie(prodotto);
    }
    
}
