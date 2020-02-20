package com.ai.ecommercej4j.service.impl;

import com.ai.ecommercej4j.model.*;
import com.ai.ecommercej4j.repository.OrdineRepository;
import com.ai.ecommercej4j.repository.UtenteRepository;
import com.ai.ecommercej4j.service.SecurityService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private UtenteRepository ur;
    @Autowired
    private OrdineRepository or;

    /**
     * genera una stringa casuale utilizzata dal double opt in
     *
     * @return stringa
     */
    private String generateRandomString() {
        double d = Math.random();
        String str = (Double.toString(d));
        return str;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto dto) {

        Utente utente = ur.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        LoginResponseDto response = new LoginResponseDto();
        if (utente != null) {
            //verifico se l'utente è un utente anonimo
            if (dto.getToken() != null) {

                Utente utenteAnonimo = ur.findByToken(dto.getToken());
                // ... se risuta positivo recupera l'ordine dell'utente nello stato carrello
                Optional<Ordine> optionalOrdine = utenteAnonimo.getOrdini().stream()
                        .filter(o -> o.getStato().equals("carrello"))
                        .findFirst();
                //verifico se l'utente anonimo ha ordini nel carrello
                if (!optionalOrdine.isEmpty()) {
                    Ordine ordine= optionalOrdine.get();
                    //aggiungo l'ordine all' utente registrato
                    utente.getOrdini().add(ordine);
                    ordine.setUtente(utente);
                    or.save(ordine);
                }
                utente.setToken(dto.getToken());
                ur.save(utente);
                //Rimuoviamo l'utente anonimo perche riconosciuto come utente registrato
                ur.deleteById(utenteAnonimo.getId());
            } else {
                String token = generateRandomString();
                utente.setToken(token);
                ur.save(utente);
            }
            response.setToken(utente.getToken());

        }

        return response;
    }

    @Override
    public void checkDoubleOptin(LoginResponseDto dto) {
        if (dto.getToken() != null) {
            String doiUtente = ur.findByDoubleOptin(dto.getToken()).getDoubleOptin();
        } else {
            System.out.println("il token non esiste");
        }
    }

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
                utente.setAnonimo(false);
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

    //ricerca anche che l'utente non sia anonimo
    @Override
    public Boolean checkToken(String tok) {
        return ur.findByTokenAndAnonimo(tok, false) != null;
        // Utente utente = ur.findByToken(tok)
        // return (utente.getToken() != null && utente.getAnonimo == false)

    }

    @Override
    public LoginResponseDto generateTokenAnonimo() {
        //Creo la risposta...
        LoginResponseDto rdto = new LoginResponseDto();
        Utente anonimo = new Utente();
        anonimo.setAnonimo(true);
        anonimo.setToken(generateRandomString());
        ur.save(anonimo);
        rdto.setToken(anonimo.getToken());
        return rdto;
    }

    @Override
    public Boolean checkAnonimo(String token) {
        return ur.findByToken(token).getAnonimo();
    }

}
