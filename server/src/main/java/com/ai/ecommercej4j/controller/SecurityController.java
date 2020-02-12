package com.ai.ecommercej4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class SecurityController {
    
    @Autowired
    SecurityService ss;
    
    @RequestMapping("/login")
    @ResponseBody
    public LoginResponseDto login(@RequestBody LoginRequestDto){
    return ss;
    }
    
    @RequestMapping("/password-dimenticata")
    @ResponseBody
    public LoginResponseDto passwordDimenticata(@RequestBody LoginRequestDto){
    return ss;
    }
    
    @RequestMapping("/check-double-optin")
    @ResponseBody
    public void checkDoubleOptin(@RequestBody LoginRequestDto){
    }
    
    @RequestMapping("/registrazione")
    @ResponseBody
    public RegistrazioneResponseDto registrami(@RequestBody RegistrazioneRequestDto){
    return ss;
    }
}
