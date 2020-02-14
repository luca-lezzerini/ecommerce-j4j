package com.ai.ecommercej4j.repository;

import com.ai.ecommercej4j.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Long>{

    public Utente findByUsernameAndPassword(String un, String pw);

    public Utente findByToken(String tk);

    public Utente findByDoubleOptin(String doi);

}
