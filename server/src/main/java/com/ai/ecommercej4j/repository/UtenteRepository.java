package com.ai.ecommercej4j.repository;

import com.ai.ecommercej4j.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Long> {

    Utente findByUsernameAndPassword(String un, String pw);

    Utente findByToken(String tk);

    Utente findByDoubleOptin(String doi);

    Utente findByUsername(String username);

    Utente findByEmail(String email);
    
    Utente findByPassword(String password);
}
