package com.ai.ecommercej4j.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AssociazioneTagliaColori {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fkIdTaglia", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "tagliaColori", allowSetters = true)
    private Taglia taglia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fkIdColori", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "tagliaColori", allowSetters = true)
    private Colori colore;

    public AssociazioneTagliaColori(Taglia taglia, Colori colore) {
        this.taglia = taglia;
        this.colore = colore;
    }

    public AssociazioneTagliaColori() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Taglia getTaglia() {
        return taglia;
    }

    public void setTaglia(Taglia taglia) {
        this.taglia = taglia;
    }

    public Colori getColore() {
        return colore;
    }

    public void setColore(Colori colore) {
        this.colore = colore;
    }
}
