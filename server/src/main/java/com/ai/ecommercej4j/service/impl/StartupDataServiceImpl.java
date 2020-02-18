package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.Utente;
import com.ai.ecommercej4j.repository.UtenteRepository;
import com.ai.ecommercej4j.service.StartupDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartupDataServiceImpl implements StartupDataService{
    @Autowired
    UtenteRepository ur;
    @Override
    public void createAllStartupData() {
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
