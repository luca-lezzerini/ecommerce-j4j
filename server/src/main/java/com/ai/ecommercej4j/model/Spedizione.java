package com.ai.ecommercej4j.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Spedizione {

    // con @Id indico la primary key
    // con @GeneratedValue assegno un valore in automatico in caso di null
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String codice;
    String descrizione;
    double prezzo;

    public Spedizione(Long id, String codice, String descrizione, double prezzo) {
        this.id = id;
        this.codice = codice;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
    }
    
    // creiamo un costruttore vuoto
    public Spedizione() {
    }
    
    // metto i getter e i setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

}
