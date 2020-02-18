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
    
    /**
     * genera una stringa casuale utilizzata dal double opt in
     * @return stringa
     */
    private String generateRandomString() {
        double d = Math.random();
        String str = (Double.toString(d));
        return str;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * cerca il doi dell'utente con la repository 
     * e lo associa ad un utente appena creato
     * se non trova il doi la stringa sarà nulla 
     * e ritornera un errorenella console 
     * @param dto che rappresenta il token di risposta del login 
     */
    @Override
    public void checkDoubleOptin(LoginResponseDto dto) {
        if(dto.getToken() != null){
        String doiUtente = ur.findByDoubleOptin(dto.getToken()).getDoubleOptin();
        }else{
            System.out.println("il token non esiste");
        }
    }
    
    /**
     * ricerca l'utenta nella repository, gli assegna il double opt in 
     * e salva l'utente.
     * Se l'utente non esiste genera un errore nella console
     * @param dto rappresenta i campi di username e password per la login
     * @return un dto di risposta contenente il double optin assegnato in caso di errore sarà vuota
     */
    @Override
    public LoginResponseDto passwordDimenticata(LoginRequestDto dto) {

        //Creo la risposta...
        LoginResponseDto rdto = new LoginResponseDto();

        //...creo l'oggetto utente e gli faccio puntare l'utente che trova con il metodo findByUsernameAndPassword...
        Utente utente = new Utente();
        utente = ur.findByUsername(dto.getUsername());
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

    /**
     * prende l' utente dalla repository e confronta la password nuova con la vecchia
     * se le password coincidono genera un errore in console altrimenti 
     * imposta la nuova password e salva l'utente.
     * @param dto 
     */
    @Override
    public void reimpostaPassword(ChangePasswordRequestDto dto) {

        //Creazione oggetto utente...
        Utente ut = new Utente();

        //...assegnazione utente esistente tramite metodo findByDoubleOptin poiche ce l'ho da prima.
        ut = ur.findByDoubleOptin(dto.getDoiCode());
       
            //Se la password nuova è diversa dalla vecchia...
            if (ut.getPassword().equals(dto.getNewPassword())) {
                System.out.println("Errore Pass Uguali");

            } else {
                //...assegno quella nuova all'utente e la salvo
                ut.setPassword(dto.getNewPassword());
                ur.save(ut);
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

    /**
     * Controlla se il token esiste
     * @param tok
     * @return vero se è diverso da null, falso in caso contrario.
     */
    @Override
    public Boolean checkToken(String tok) {
        return ur.findByToken(tok) != null;
    }

}
