package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.Colori;
import com.ai.ecommercej4j.model.Prodotto;
import com.ai.ecommercej4j.model.ProdottoTaglia;
import com.ai.ecommercej4j.model.Taglia;
import com.ai.ecommercej4j.model.TagliaColori;
import com.ai.ecommercej4j.model.TagliaColoriRequestDto;
import com.ai.ecommercej4j.model.TagliaColoriResponseDto;
import com.ai.ecommercej4j.model.TagliaColoriUpdateDto;
import com.ai.ecommercej4j.repository.ColoriRepository;
import com.ai.ecommercej4j.repository.ProdottoTagliaRepository;
import com.ai.ecommercej4j.repository.TagliaColoriRepository;
import com.ai.ecommercej4j.service.SecurityService;
import com.ai.ecommercej4j.service.TagliaColoriService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagliaColoriServiceImpl implements TagliaColoriService {

    @Autowired
    private SecurityService ss;

    @Autowired
    private TagliaColoriRepository tcr;

    @Autowired
    private ProdottoTagliaRepository ptr;

    @Autowired
    private ColoriRepository cr;

    @Override
    public TagliaColoriResponseDto richiediTagliaColori(TagliaColoriRequestDto dto) {
        TagliaColoriResponseDto response = new TagliaColoriResponseDto();
        String token = dto.getToken();
        Taglia taglia = dto.getTaglia();
        Prodotto prodotto = dto.getProdotto();
        if (ss.checkToken(token)) {
            ProdottoTaglia pt = ptr.findByProdottoIdAndTagliaId(prodotto.getId(), taglia.getId());
            List<Colori> listaColoriAssociati = new ArrayList<>();
            for (TagliaColori tc : pt.getTagliaColori()) {
                listaColoriAssociati.add(tc.getColore());
            }
//            System.out.println("\n\n\n\n\n\n");
//            for (Colori colori : listaColoriAssociati) {
//                System.out.println(colori.getDescrizione());
//            }
//            System.out.println("\n\n\n\n\n\n");
            response.setListaColoriAssociati(listaColoriAssociati);
            response.setListaColori(cr.findAll());
        } else {
            response.setListaColori(Collections.emptyList());
            response.setListaColoriAssociati(Collections.emptyList());
        }
        return response;
    }

    @Override
    public void aggiungiTagliaColori(TagliaColoriUpdateDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
