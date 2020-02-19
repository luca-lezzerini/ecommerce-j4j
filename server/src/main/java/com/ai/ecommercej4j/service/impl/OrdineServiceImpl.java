package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.OrdineCreateDto;
import com.ai.ecommercej4j.repository.OrdineRepository;
import com.ai.ecommercej4j.service.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdineServiceImpl implements OrdineService {
   
    @Autowired 
    OrdineRepository ordineRepository;

    @Override
    public void addCarrello(OrdineCreateDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
