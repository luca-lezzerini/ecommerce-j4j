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
        Utente utente = ur.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());;
        LoginResponseDto response = new LoginResponseDto();
        if (utente != null) {
            String token = generateRandomString();
            utente.setToken(token);
            ur.save(utente);
            response.setToken(utente.getToken());
        }
        return response;
    }

    @Override
    public void checkDoubleOptin(LoginResponseDto dto) {
        // cerco il doi dell'utente con la repository e lo associo a quello creato
        // se non trovo il doi la stringa sarà nulla
        if(dto.getToken() != null){
        String doiUtente = ur.findByDoubleOptin(dto.getToken()).getDoubleOptin();
        }else{
            System.out.println("il token non esiste");
        }
        // se è null perche non trova niente, torna un errore sul client
    }

    @Override
    public LoginResponseDto passwordDimenticata(LoginRequestDto dto) {

        //Creo la risposta...
        LoginResponseDto rdto = new LoginResponseDto();

        //...creo l'oggetto utente e gli faccio puntare l'utente che trova con il metodo findByUsernameAndPassword...
        Utente utente = new Utente();
        utente = ur.findByUsername(dto.getUsername());
        System.out.println(utente);
        if (utente != null) {
            String doi = generateRandomString();

            // utilizzo il metodo setDoubleOptin in quanto in utente ho dichiarato la variabile doubleOptin
            utente.setDoubleOptin(doi);

            // token in questo caso sta per double optin
            rdto.setToken(doi);

            ur.save(utente);
            //...ritorno il token tramite il LoginResponseDto rdto.
            return rdto;
        } else {
            System.out.println("l'username non esiste");
            return rdto;
        }

    }

    @Override
    public void reimpostaPassword(ChangePasswordRequestDto dto) {

        //Creazione oggetto utente...
        Utente ut = new Utente();

        //...assegnazione utente esistente tramite metodo findByDoubleOptin poiche ce l'ho da prima.
        ut = ur.findByDoubleOptin(dto.getDoiCode());
        if (ut.getDoubleOptin() != null) {
            System.out.println(ut);
            System.out.println(ut.getPassword());

            //Se la password nuova è diversa dalla vecchia...
            if (ut.getPassword().equals(dto.getNewPassword())) {
                System.out.println("Errore Pass Uguali");

            } else {
                System.out.println("sono nell'if");
                //...assegno quella nuova all'utente e la salvo
                ut.setPassword(dto.getNewPassword());
                ur.save(ut);
            }
        }else{
            System.out.println("il doubleOptIn non esiste");
        }
    }

    @Override
    public RegistrazioneResponseDto registrami(RegistrazioneRequestDto dto) {

        RegistrazioneResponseDto resp = new RegistrazioneResponseDto();
        resp.setRegistrato(false);

        // Verifico che i dati siano disponibili...
        if (ur.findByUsername(dto.getUsername()) == null) {
            // ...se l'username è disponibile controllo che lo sia anche la mail...
            if (ur.findByEmail(dto.getEmail()) == null) {
                // ...se la mail è disponibile, registro l'utente e avviso che è stato registrato
                Utente utente = new Utente();
                utente.setUsername(dto.getUsername());
                utente.setPassword(dto.getPassword());
                utente.setEmail(dto.getEmail());
                ur.save(utente);
                resp.setRegistrato(true);
            } else {
                // ...altrimenti non lo registro e scrivo un messaggio dicendo che la mail già esiste
                resp.setMessaggio("Indirizzo email già esistente");
            }
        } else {
            // ...altrimenti controllo se la mail esiste...
            if (ur.findByEmail(dto.getEmail()) != null) {
                // ...se esiste avviso che entrambi i campi già sono esistenti
                resp.setMessaggio("Indirizzo email e username già esistenti");
            } else {
                // ...se non esiste avviso che solo l'username è già esistente
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
