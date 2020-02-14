package com.ai.ecommercej4j.repository;

import com.ai.ecommercej4j.model.ColoriCreateDto;
import com.ai.ecommercej4j.model.ColoriDeleteDto;
import com.ai.ecommercej4j.model.ColoriSearchDto;
import com.ai.ecommercej4j.model.ColoriSearchResultsDto;
import com.ai.ecommercej4j.model.ColoriUpdateDto;
import org.springframework.stereotype.Repository;

@Repository("coloriRepository")
public interface ColoriRepository {
    
    public ColoriSearchResultsDto findColori(ColoriSearchDto dto);
    public void saveColori(ColoriCreateDto dto);
    public void deleteColori(ColoriDeleteDto dto);
    public void updateColori(ColoriUpdateDto dto);
    
}
