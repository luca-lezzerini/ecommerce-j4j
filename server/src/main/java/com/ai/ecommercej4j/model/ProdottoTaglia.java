/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author utente
 */
@Entity
public class ProdottoTaglia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prodotto_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "prodottoTaglia", allowSetters = true)
    private Prodotto prodotto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taglia_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "tagliaProdotto", allowSetters = true)
    private Taglia taglia;

    @OneToMany(mappedBy = "prodottoTaglia", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = "prodottoTaglia", allowSetters = true)
    private List<TagliaColori> tagliaColori = new ArrayList<>();

    public List<TagliaColori> getTagliaColori() {
        return tagliaColori;
    }

    public void setTagliaColori(List<TagliaColori> tagliaColori) {
        this.tagliaColori = tagliaColori;
    }

    public ProdottoTaglia() {
    }

    public ProdottoTaglia(Long id, Prodotto prodotto, Taglia taglia) {
        this.id = id;
        this.prodotto = prodotto;
        this.taglia = taglia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public Taglia getTaglia() {
        return taglia;
    }

    public void setTaglia(Taglia taglia) {
        this.taglia = taglia;
    }

}
