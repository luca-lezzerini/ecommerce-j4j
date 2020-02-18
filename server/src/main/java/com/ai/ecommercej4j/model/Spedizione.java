package com.ai.ecommercej4j.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/*Per Fabio : Inserire  @Entity & @Table nello spazio sottostante e 
in seguito relativi import -Io non riesco ad importarli-
 */
@Entity
@Table
public class Spedizione {

    //X Fabio : nello spazio sottostante inserire annotazioni id  e generated value con relativi import
    //Da valutare l'insert di equals
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
