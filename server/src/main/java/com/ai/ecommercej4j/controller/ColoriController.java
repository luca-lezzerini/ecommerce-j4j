package com.ai.ecommercej4j.controller;


import com.ai.ecommercej4j.model.ColoriCreateDto;
import com.ai.ecommercej4j.model.ColoriDeleteDto;
import com.ai.ecommercej4j.model.ColoriSearchDto;
import com.ai.ecommercej4j.model.ColoriSearchResultsDto;
import com.ai.ecommercej4j.model.ColoriUpdateDto;
import com.ai.ecommercej4j.service.ColoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    
    @RequestMapping("/search-colori-per-descrizione")
    @ResponseBody
    public ColoriSearchResultsDto searchColoriPerDescrizione(@RequestBody ColoriSearchDto dto){
        return cs.searchColoriPerDescrizione(dto);
    }
}
