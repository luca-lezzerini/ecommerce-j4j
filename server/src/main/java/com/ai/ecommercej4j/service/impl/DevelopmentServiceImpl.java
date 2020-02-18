package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.Prodotto;
import com.ai.ecommercej4j.model.Utente;
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
        // elimina tutti dati dal db
        pr.deleteAllInBatch();
        ur.deleteAllInBatch();
        sr.deleteAllInBatch();
        tr.deleteAllInBatch();
        cr.deleteAllInBatch();
        
        //crea cliente
        Utente cliente = new Utente();
        cliente.setUsername("cliente");
        cliente.setPassword("cliente");
        
        // crea gestore
        Utente gestore = new Utente();
        gestore.setUsername("gestore");
        gestore.setPassword("gestore");
        //salva
        ur.save(cliente);
        ur.save(gestore);
        
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
    
}
