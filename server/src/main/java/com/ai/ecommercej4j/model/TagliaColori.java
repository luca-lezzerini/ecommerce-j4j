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
public class TagliaColori {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fkIdProdottoTaglia", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "tagliaColori", allowSetters = true)
    private ProdottoTaglia prodottoTaglia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fkIdColori", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "tagliaColori", allowSetters = true)
    private Colori colore;

    public ProdottoTaglia getProdottoTaglia() {
        return prodottoTaglia;
    }

    public void setProdottoTaglia(ProdottoTaglia prodottoTaglia) {
        this.prodottoTaglia = prodottoTaglia;
    }

    public TagliaColori(ProdottoTaglia prodottoTaglia, Colori colore) {
        this.prodottoTaglia = prodottoTaglia;
        this.colore = colore;
    }

    public TagliaColori() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Colori getColore() {
        return colore;
    }

    public void setColore(Colori colore) {
        this.colore = colore;
    }
}
