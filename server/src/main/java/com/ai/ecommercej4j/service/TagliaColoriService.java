package com.ai.ecommercej4j.service;

import com.ai.ecommercej4j.model.TagliaColoriRequestDto;
import com.ai.ecommercej4j.model.TagliaColoriResponseDto;
import com.ai.ecommercej4j.model.TagliaColoriUpdateDto;

public interface TagliaColoriService {

    TagliaColoriResponseDto richiediTagliaColori(TagliaColoriRequestDto dto);

    TagliaColoriResponseDto aggiungiTagliaColori(TagliaColoriUpdateDto dto);
    
    TagliaColoriResponseDto rimuoviTagliaColori(TagliaColoriUpdateDto dto);
}
