package com.ai.ecommercej4j.repository;

public interface UtenteRepository {
    
    public Utente findByUsernameAndPassword(String un, String pw);
    public Utente findByToken(String tk);
    public Utente findByDoubleOptin(String doi);
    
}
