package com.ai.ecommercej4j.service;

import com.ai.ecommercej4j.model.*;

public interface SecurityService {

    LoginResponseDto login(LoginRequestDto dto);

    /**
     * cerca il doi dell'utente con la repository e lo associa ad un utente
     * appena creato se non trova il doi la stringa sarà nulla e ritornera un
     * errorenella console
     *
     * @param dto che rappresenta il token di risposta del login
     */
    void checkDoubleOptin(LoginResponseDto dto);

    /**
     * ricerca l'utenta nella repository, gli assegna il double opt in e salva
     * l'utente. Se l'utente non esiste genera un errore nella console
     *
     * @param dto rappresenta i campi di username e password per la login
     * @return un dto di risposta contenente il double optin assegnato in caso
     * di errore sarà vuota
     */
    LoginResponseDto passwordDimenticata(LoginRequestDto dto);

    RegistrazioneResponseDto registrami(RegistrazioneRequestDto dto);

    /**
     * prende l' utente dalla repository e confronta la password nuova con la
     * vecchia se le password coincidono genera un errore in console altrimenti
     * imposta la nuova password e salva l'utente.
     *
     * @param dto
     */
    void reimpostaPassword(ChangePasswordRequestDto dto);

    /**
     * Controlla se il token esiste
     *
     * @param tok
     * @return vero se è diverso da null, falso in caso contrario.
     */
    Boolean checkToken(String tok);
    
    /**
     * Crea un token per l'utente anonimo usando il metodo generateRandomString
     *
     * @return un dto di risposta contenente il token
     */
    LoginResponseDto generateTokenAnonimo();

}
