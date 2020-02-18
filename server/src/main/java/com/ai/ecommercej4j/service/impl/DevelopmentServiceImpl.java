package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.Prodotto;
import com.ai.ecommercej4j.repository.*;
import com.ai.ecommercej4j.service.DevelopmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevelopmentServiceImpl implements DevelopmentService{

    @Autowired
    ProdottoRepository pr;
    @Autowired
    UtenteRepository ur;
    @Autowired
    SpedizioneRepository sr;
    @Autowired
    TagliaRepository tr;
    @Autowired
    ColoriRepository cr;
    
    @Override
    public void generateTestData() {   
        
        Prodotto p1 = new Prodotto();
        p1.setPrezzo(12.50);
        p1.setCodice("32");
        p1.setDescrizione("offerta");
        p1.setOfferta(true);
        pr.save(p1);
        
        Prodotto p2 = new Prodotto();
        p2.setPrezzo(25.90);
        p2.setCodice("31");
        p2.setDescrizione("no offerta");
        p2.setOfferta(false);
        pr.save(p2);
        
        Prodotto p3 = new Prodotto();
        p3.setPrezzo(6.50);
        p3.setCodice("32");
        p3.setDescrizione("offerta");
        p3.setOfferta(true);
        pr.save(p3);
        
        Prodotto p4 = new Prodotto();
        p4.setPrezzo(39.64);
        p4.setCodice("31");
        p4.setDescrizione("no offerta");
        p4.setOfferta(false);
        pr.save(p4);        
    }

    @Override
    public void dropDataBase() {
        // elimina tutti dati dal db
        pr.deleteAllInBatch();
        ur.deleteAllInBatch();
        sr.deleteAllInBatch();
        tr.deleteAllInBatch();
        cr.deleteAllInBatch();        
    }
    
}
