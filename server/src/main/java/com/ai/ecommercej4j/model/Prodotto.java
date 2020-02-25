package com.ai.ecommercej4j.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Prodotto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String codice;
    private String descrizione;
    private double prezzo;
    private boolean offerta;
    @OneToMany(mappedBy = "prodotto", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = "prodotto", allowSetters = true)
    private List<RigaOrdine> righe = new ArrayList<>();
    
    @OneToMany(mappedBy = "prodotto", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = "prodotto", allowSetters = true)
    private List<ProdottoTaglia> prodottoTaglia;

    public List<ProdottoTaglia> getProdottoTaglia() {
        return prodottoTaglia;
    }

    public void setProdottoTaglia(List<ProdottoTaglia> prodottoTaglia) {
        this.prodottoTaglia = prodottoTaglia;
    }
    
    
    public List<RigaOrdine> getRighe() {
        return righe;
    }

    public void setRighe(List<RigaOrdine> righe) {
        this.righe = righe;
    }

    public Prodotto() {
    }

    public Prodotto(Long id, String codice, String descrizione, double prezzo, boolean offerta) {
        this.id = id;
        this.codice = codice;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.offerta = offerta;
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

    public boolean isOfferta() {
        return offerta;
    }

    public void setOfferta(boolean offerta) {
        this.offerta = offerta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.codice);
        hash = 97 * hash + Objects.hashCode(this.descrizione);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.prezzo) ^ (Double.doubleToLongBits(this.prezzo) >>> 32));
        hash = 97 * hash + (this.offerta ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prodotto other = (Prodotto) obj;
        if (Double.doubleToLongBits(this.prezzo) != Double.doubleToLongBits(other.prezzo)) {
            return false;
        }
        if (this.offerta != other.offerta) {
            return false;
        }
        if (!Objects.equals(this.codice, other.codice)) {
            return false;
        }
        if (!Objects.equals(this.descrizione, other.descrizione)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
