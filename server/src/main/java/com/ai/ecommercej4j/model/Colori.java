package com.ai.ecommercej4j.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Colori implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String codice;
    @Column
    private String descrizione;

    @OneToMany(mappedBy = "colore", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = "colore", allowSetters = true)
    private List<AssociazioneTagliaColori> tagliaColori = new ArrayList<>();

    public List<AssociazioneTagliaColori> getTagliaColori() {
        return tagliaColori;
    }

    public void setTagliaColori(List<AssociazioneTagliaColori> tagliaColori) {
        this.tagliaColori = tagliaColori;
    }

    public Colori(Long id, String codice, String descrizione) {
        this.id = id;
        this.codice = codice;
        this.descrizione = descrizione;
    }

    public Colori() {
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
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
}
