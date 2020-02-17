package com.ai.ecommercej4j.controller;

import com.ai.ecommercej4j.service.DevelopmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class DevelopmentController {
    
    @Autowired
    DevelopmentService ds;
    
    @RequestMapping("/generate-test-data")
    @ResponseBody
    public void generateTestData(){
        ds.generateTestData();
    }
    
}
