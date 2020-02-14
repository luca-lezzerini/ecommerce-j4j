
package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.Colori;
import com.ai.ecommercej4j.model.ColoriCreateDto;
import com.ai.ecommercej4j.model.ColoriDeleteDto;
import com.ai.ecommercej4j.model.ColoriSearchDto;
import com.ai.ecommercej4j.model.ColoriSearchResultsDto;
import com.ai.ecommercej4j.model.ColoriUpdateDto;
import com.ai.ecommercej4j.repository.ColoriRepository;
import com.ai.ecommercej4j.service.ColoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("coloriService")
public class ColoriServiceImpl implements ColoriService {
    
    @Autowired
    private ColoriRepository coloriRepository;
    
    @Override
    public void createColori(ColoriCreateDto dto) {
        Colori colore = new Colori();
        colore.setDescrizione(colore.getDescrizione());
        colore.setCodice(colore.getCodice());
        colore.setId(colore.getId());
        dto.setDati(colore);
        coloriRepository.saveColori(dto);
    }

    @Override
    public ColoriSearchResultsDto searchColori(ColoriSearchDto dto) {
        return coloriRepository.findColori(dto);
    }

    @Override
    public void deleteColori(ColoriDeleteDto dto) {
    coloriRepository.deleteColori(dto);
    }

    @Override
    public void updateColori(ColoriUpdateDto dto) {
    coloriRepository.updateColori(dto);
    }
    
}
