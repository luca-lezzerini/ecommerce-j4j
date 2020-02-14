package com.ai.ecommercej4j.service;

import com.ai.ecommercej4j.model.TagliaCreateDto;
import com.ai.ecommercej4j.model.TagliaDeleteDto;
import com.ai.ecommercej4j.model.TagliaSearchDto;
import com.ai.ecommercej4j.model.TagliaSearchResultsDto;
import com.ai.ecommercej4j.model.TagliaUpdateDto;

public interface TagliaService {

    void createTaglia(TagliaCreateDto dto);

    TagliaSearchResultsDto searchTaglia(TagliaSearchDto dto);

    void deleteTaglia(TagliaDeleteDto dto);

    void updateTaglia(TagliaUpdateDto dto);
}
