package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.*;
import com.ai.ecommercej4j.repository.UtenteRepository;
import com.ai.ecommercej4j.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private UtenteRepository ur;

    private String generateRandomString() {
        double d = Math.random();
        String str = (Double.toString(d));
        return str;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void checkDoubleOptin(LoginResponseDto dto) {

    }

    @Override
    public LoginResponseDto passwordDimenticata(LoginRequestDto dto) {
        
        //Creo la risposta...
        LoginResponseDto rdto = new LoginResponseDto();
        
        //...creo l'oggetto utente e gli faccio puntare l'utente che trova con il metodo findByUsernameAndPassword...
        Utente utente = new Utente();
        utente = ur.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        
        //...nella risposta ci metto il token dell'utente che ho trovato...
        rdto.setToken("" + ur.findByToken(utente.getToken()));

        //...ritorno il token tramite il LoginResponseDto rdto.
        return rdto;
    }

    @Override
    public void reimpostaPassword(ChangePasswordRequestDto dto) {

    }

    @Override
public RegistrazioneResponseDto registrami(RegistrazioneRequestDto dto) {
	
	RegistrazioneResponseDto resp = new RegistrazioneResponseDto();
	resp.setRegistrato(false);
	
	// Verifico che i dati siano disponibili...
	if (ur.findByUsername(dto.getUsername()) == null) {
		// ...se l'username è disponibile controllo che lo sia anche la mail---
		if (ur.findByEmail(dto.getEmail()) == null) {
			// ---se la mail è disponibile, registro l'utente e avviso che è stato registrato
			Utente utente = new Utente();
			utente.setUsername(dto.getUsername());
			utente.setPassword(dto.getPassword());
			utente.setEmail(dto.getEmail());
			ur.save(utente);
			resp.setRegistrato(true);
		} else {
			// ---altrimenti non lo registro e scrivo un messaggio dicendo che la mail già esiste
			resp.setMessaggio("Indirizzo email già esistente");
		}
	} else {
		// ...altrimenti controllo se la mail esiste---
		if (ur.findByEmail(dto.getEmail()) != null) {
			// ---se esiste avviso che entrambi i campi già sono esistenti
			resp.setMessaggio("Indirizzo email e username già esistenti");
		} else {
			// ---se non esiste avviso che solo l'username è già esistente
			resp.setMessaggio("Username già esistente");
		}
	}
	// ritorno una risposta
	return resp;
}

    @Override
    public Boolean checkToken(String tok) {
        return ur.findByToken(tok) != null;
    }

}
