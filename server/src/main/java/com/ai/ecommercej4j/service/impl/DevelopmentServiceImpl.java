package com.ai.ecommercej4j.service.impl;

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
    }
    
}
