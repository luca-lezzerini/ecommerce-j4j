package com.ai.ecommercej4j.controller;


import com.ai.ecommercej4j.model.*;
import com.ai.ecommercej4j.service.ColoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class ColoriController {
    @Autowired
    ColoriService cs;
    
    @RequestMapping("/create-colori")
    @ResponseBody
    public void createColori(@RequestBody ColoriCreateDto dto) {
         cs.createColori(dto);
    }
    
    @RequestMapping("/search-colori")
    @ResponseBody
    public ColoriSearchResultsDto searchColori(@RequestBody ColoriSearchDto dto) {
         return cs.searchColori(dto);
    }
    
    @RequestMapping("/search-colori-per-descrizione")
    @ResponseBody
    public ColoriSearchResultsDto searchColoriPerDescrizione(@RequestBody ColoriSearchDto dto) {
         return cs.searchColoriPerDescrizione(dto);
    }
    
    @RequestMapping("/delete-colori")
    @ResponseBody
    public void deleteColori(@RequestBody ColoriDeleteDto dto) {
         cs.deleteColori(dto);
    }
    
    @RequestMapping("/update-colori")
    @ResponseBody
    public void updateColori(@RequestBody ColoriUpdateDto dto) {
         cs.updateColori(dto);
    }
    
}
