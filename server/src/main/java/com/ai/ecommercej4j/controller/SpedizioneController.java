/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.controller;

import com.ai.ecommercej4j.model.SpedizioneCreateDto;
import com.ai.ecommercej4j.model.SpedizioneDeleteDto;
import com.ai.ecommercej4j.model.SpedizioneSearchDto;
import com.ai.ecommercej4j.model.SpedizioneSearchResultsDto;
import com.ai.ecommercej4j.model.SpedizioneUpdateDto;
import com.ai.ecommercej4j.service.SpedizioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class SpedizioneController {

    @Autowired
    SpedizioneService ss;

    @RequestMapping("/create-spedizione")
    @ResponseBody
    public void createSpedizione(@RequestBody SpedizioneCreateDto dto) {
        
    }

    @RequestMapping("/search-spedizione")
    @ResponseBody
   public SpedizioneSearchResultsDto searchSpedizione(@RequestBody SpedizioneSearchDto dto) {
        return ss.searchSpedizione(dto);
    
   }
    @RequestMapping("/delete-spedizione")
    @ResponseBody
    public void deleteSpedizione(@RequestBody SpedizioneDeleteDto dto) {
       
    }

    @RequestMapping("/update-spedizione")
    @ResponseBody
    public void updateSpedizione(SpedizioneUpdateDto dto) {

    }

}
