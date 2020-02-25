/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author utente
 */
@Entity
public class Taglia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String codice;
    private String descrizione;

    @OneToMany(mappedBy = "taglia", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = "taglia", allowSetters = true)
    private List<ProdottoTaglia> tagliaProdotto = new ArrayList<>();

    @OneToMany(mappedBy = "taglia", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = "taglia", allowSetters = true)
    private List<AssociazioneTagliaColori> tagliaColori = new ArrayList<>();

    public List<AssociazioneTagliaColori> getTagliaColori() {
        return tagliaColori;
    }

    public void setTagliaColori(List<AssociazioneTagliaColori> tagliaColori) {
        this.tagliaColori = tagliaColori;
    }

    public List<ProdottoTaglia> getTagliaProdotto() {
        return tagliaProdotto;
    }

    public void setTagliaProdotto(List<ProdottoTaglia> tagliaProdotto) {
        this.tagliaProdotto = tagliaProdotto;
    }

    public Taglia() {
    }

    public Taglia(String codice, String descrizione) {
        this.codice = codice;
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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

}
