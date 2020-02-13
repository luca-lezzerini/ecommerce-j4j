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
    public LoginResponseDto login(@RequestBody LoginRequestDto dto) {
        return ss.login(dto);
    }

    @RequestMapping("/password-dimenticata")
    @ResponseBody
    public LoginResponseDto passwordDimenticata(@RequestBody LoginRequestDto dto) {
        return ss.passwordDimenticata(dto);
    }

    @RequestMapping("/check-double-optin")
    @ResponseBody
    public void checkDoubleOptin(@RequestBody LoginResponseDto dto) {
        ss.checkDoubleOptin(dto);
    }

    @RequestMapping("/registrazione")
    @ResponseBody
    public RegistrazioneResponseDto registrami(@RequestBody RegistrazioneRequestDto dto) {
        return ss.registrami(dto);
    }

    @RequestMapping("/reimposta-password")
    @ResponseBody
    public void reimpostaPassword(@RequestBody ChangePasswordRequestDto dto) {
        ss.reimpostaPassword(dto);
    }

}
