package com.ai.ecommercej4j.controller;

import com.ai.ecommercej4j.model.TagliaColoriRequestDto;
import com.ai.ecommercej4j.model.TagliaColoriResponseDto;
import com.ai.ecommercej4j.model.TagliaColoriUpdateDto;
import com.ai.ecommercej4j.service.TagliaColoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class TagliaColoriController {

    @Autowired
    TagliaColoriService ts;

    @RequestMapping(value = "/richiedi-taglia-colori")
    public TagliaColoriResponseDto richiediTagliaColori(@RequestBody TagliaColoriRequestDto dto) {

        return ts.richiediTagliaColori(dto);
    }

    @RequestMapping(value = "/aggiungi-taglia-colori")
    public void aggiungiTagliaColori(@RequestBody TagliaColoriUpdateDto dto) {
        ts.aggiungiTagliaColori(dto);
    }

}
