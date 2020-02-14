package com.ai.ecommercej4j.controller;

import com.ai.ecommercej4j.model.TagliaCreateDto;
import com.ai.ecommercej4j.model.TagliaDeleteDto;
import com.ai.ecommercej4j.model.TagliaSearchDto;
import com.ai.ecommercej4j.model.TagliaSearchResultsDto;
import com.ai.ecommercej4j.model.TagliaUpdateDto;
import com.ai.ecommercej4j.service.TagliaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class TagliaController {

    @Autowired
    TagliaService ts;

    @RequestMapping(value = "/create-taglia")
    public void createTaglia(@RequestBody TagliaCreateDto dto) {
        ts.createTaglia(dto);
    }

    @RequestMapping(value = "/search-taglia")
    public TagliaSearchResultsDto searchTaglia(@RequestBody TagliaSearchDto dto) {
        return ts.searchTaglia(dto);
    }

    @RequestMapping(value = "/delete-taglia")
    public void deleteTaglia(@RequestBody TagliaDeleteDto dto) {

    }

    @RequestMapping(value = "/update-taglia")
    public void updateTaglia(@RequestBody TagliaUpdateDto dto) {

    }
}
