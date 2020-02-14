/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.model;

public class Spedizione {
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

    public Spedizione() {
    }
    

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
