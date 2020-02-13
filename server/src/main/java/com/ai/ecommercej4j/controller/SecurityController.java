package com.ai.ecommercej4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ai.ecommercej4j.model.*;
import com.ai.ecommercej4j.service.SecurityService;

@RestController
@CrossOrigin("*")
public class SecurityController {
    
    @Autowired
    SecurityService ss;
    
    @RequestMapping("/login")
    @ResponseBody
    public LoginResponseDto login(@RequestBody LoginRequestDto dto){
    return ss;
    }
    
    @RequestMapping("/password-dimenticata")
    @ResponseBody
    public LoginResponseDto passwordDimenticata(@RequestBody LoginRequestDto dto){
    return ss;
    }
    
    @RequestMapping("/check-double-optin")
    @ResponseBody
    public void checkDoubleOptin(@RequestBody LoginRequestDto dto){
    }
    
    @RequestMapping("/registrazione")
    @ResponseBody
    public RegistrazioneResponseDto registrami(@RequestBody RegistrazioneRequestDto dto){
    return ss;
    }
}
