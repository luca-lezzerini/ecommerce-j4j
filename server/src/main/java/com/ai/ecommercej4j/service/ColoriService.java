
package com.ai.ecommercej4j.service;

import com.ai.ecommercej4j.model.ColoriCreateDto;
import com.ai.ecommercej4j.model.ColoriDeleteDto;
import com.ai.ecommercej4j.model.ColoriSearchDto;
import com.ai.ecommercej4j.model.ColoriSearchResultsDto;
import com.ai.ecommercej4j.model.ColoriUpdateDto;


public interface ColoriService {
    public void createColori(ColoriCreateDto dto);
    public ColoriSearchResultsDto searchColori(ColoriSearchDto dto);
    public ColoriSearchResultsDto searchColoriXDescrizione(ColoriSearchDto dto);
    public void deleteColori(ColoriDeleteDto dto);
    public void updateColori(ColoriUpdateDto dto);
}
