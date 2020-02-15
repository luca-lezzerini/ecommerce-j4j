package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.Taglia;
import com.ai.ecommercej4j.model.TagliaCreateDto;
import com.ai.ecommercej4j.model.TagliaDeleteDto;
import com.ai.ecommercej4j.model.TagliaSearchDto;
import com.ai.ecommercej4j.model.TagliaSearchResultsDto;
import com.ai.ecommercej4j.model.TagliaUpdateDto;
import com.ai.ecommercej4j.repository.TagliaRepository;
import com.ai.ecommercej4j.service.SecurityService;
import com.ai.ecommercej4j.service.TagliaService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagliaServiceImpl implements TagliaService {

    @Autowired
    private SecurityService ss;

    @Autowired
    private TagliaRepository tr;

    @Override
    public void createTaglia(TagliaCreateDto dto) {
        //verifico che il token sia registrato...
        if (ss.checkToken(dto.getToken())) {
            //...se è registrato verifico che non esista una taglia con lo stesso codice...   
            if (tr.findByCodice(dto.getDati().getCodice()) != null) {
                //...se non esiste la salvo nel db
                tr.save(dto.getDati());
            }
                //...se esiste non faccio nulla
        }
            //...se il token non è registrato non faccio nulla
    }

    @Override
    public TagliaSearchResultsDto searchTaglia(TagliaSearchDto dto) {
        TagliaSearchResultsDto ris = new TagliaSearchResultsDto();
        // FIXME : stub
        ArrayList<Taglia> arr = new ArrayList<>();
        arr.add(new Taglia("s", "small"));
        arr.add(new Taglia("m", "medium"));
        arr.add(new Taglia("l", "large"));
        ris.setResult(arr);
        // end stub
        // TODO : ricerca delle taglie
        return ris;
    }

    @Override
    public void deleteTaglia(TagliaDeleteDto dto) {

    }

    @Override
    public void updateTaglia(TagliaUpdateDto dto) {

    }

}
