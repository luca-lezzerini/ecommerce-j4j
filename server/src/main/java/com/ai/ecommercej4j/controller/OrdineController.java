package com.ai.ecommercej4j.controller;

import com.ai.ecommercej4j.model.LoginResponseDto;
import com.ai.ecommercej4j.model.OrdineCreateDto;
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
    
    @RequestMapping("/view-carrello")
    @ResponseBody
    public void viewCarrello(@RequestBody LoginResponseDto dto){
        ordineService.viewCarrello(dto);
    }
   
}
