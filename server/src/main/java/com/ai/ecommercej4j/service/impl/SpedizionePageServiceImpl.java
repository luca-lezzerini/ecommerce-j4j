/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.Spedizione;
import com.ai.ecommercej4j.repository.SpedizionePageRepository;
import com.ai.ecommercej4j.service.SpedizionePageService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SpedizionePageServiceImpl implements SpedizionePageService{
        
    @Autowired
    SpedizionePageRepository repository;
     
    public List<Spedizione> getAllSpedizione(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
 
        Page<Spedizione> pagedResult = repository.findAll(paging);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Spedizione>();
        }
    }
}


