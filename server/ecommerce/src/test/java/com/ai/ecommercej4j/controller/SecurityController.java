/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.controller;

import com.ai.ecommercej4j.model.LoginRequestDto;
import com.ai.ecommercej4j.model.LoginResponseDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController

public class SecurityController {
    @RequestMapping(value = "/login")
    @ResponseBody
    public LoginResponseDto login(@RequestBody LoginRequestDto){
        System.out.println("Siamo in login!");
    }
    public LoginResponseDto passwordDimenticata(@RequestBody LoginRequestDto){
        System.out.println("Siamo in passwordDimenticata!");
    }

}
