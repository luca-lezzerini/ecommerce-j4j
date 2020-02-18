package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.Prodotto;
import com.ai.ecommercej4j.repository.*;
import com.ai.ecommercej4j.service.DevelopmentService;
import com.ai.ecommercej4j.service.StartupDataService;
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
    @Autowired
    StartupDataService sds;
    
    @Override
    public void generateTestData() {   
        
        // elimina tutti i dati presenti sul db
        dropDataBase();
        
        // inserimento dati di startup
        sds.createAllStartupData();  
        
        // inserimento prodotti
        generateProdotti();
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

    @Override
    public void generateProdotti() {
        
        Prodotto p = new Prodotto();
        p.setPrezzo(12.50);
        p.setCodice("32");
        p.setDescrizione("calzini");
        p.setOfferta(true);
        pr.save(p);
        
        p.setPrezzo(25.90);
        p.setCodice("15");
        p.setDescrizione("maglia");
        p.setOfferta(false);
        pr.save(p);
        
        p.setPrezzo(6.50);
        p.setCodice("88");
        p.setDescrizione("felpa");
        p.setOfferta(true);
        pr.save(p);
        
        p.setPrezzo(39.64);
        p.setCodice("51");
        p.setDescrizione("jeans");
        p.setOfferta(true);
        pr.save(p);      
        
        p.setPrezzo(9.00);
        p.setCodice("34");
        p.setDescrizione("calzini");
        p.setOfferta(true);
        pr.save(p);
        
        p.setPrezzo(29.99);
        p.setCodice("19");
        p.setDescrizione("cappello");
        p.setOfferta(false);
        pr.save(p);        
        
        p.setPrezzo(54.01);
        p.setCodice("60");
        p.setDescrizione("jeans slim");
        p.setOfferta(true);
        pr.save(p);
        
        p.setPrezzo(9.89);
        p.setCodice("77");
        p.setDescrizione("sciarpa");
        p.setOfferta(true);
        pr.save(p);     
        
        p.setPrezzo(4.02);
        p.setCodice("42");
        p.setDescrizione("maglioncino");
        p.setOfferta(false);
        pr.save(p);
        
        p.setPrezzo(269.99);
        p.setCodice("27");
        p.setDescrizione("felpa con cappuccio");
        p.setOfferta(false);
        pr.save(p);        
        
        p.setPrezzo(152.00);
        p.setCodice("43");
        p.setDescrizione("scarpe sportive");
        p.setOfferta(true);
        pr.save(p);
        
        p.setPrezzo(44.64);
        p.setCodice("84");
        p.setDescrizione("scarpe di pelle");
        p.setOfferta(false);
        pr.save(p);        
        
        p.setPrezzo(85.50);
        p.setCodice("21");
        p.setDescrizione("camicia");
        p.setOfferta(false);
        pr.save(p);
        
        p.setPrezzo(10.00);
        p.setCodice("30");
        p.setDescrizione("cravatta");
        p.setOfferta(false);
        pr.save(p);        
    }
}
