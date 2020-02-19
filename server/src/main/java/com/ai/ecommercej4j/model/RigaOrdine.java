package com.ai.ecommercej4j.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RigaOrdine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private int qta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fkIdOrdine", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "righe", allowSetters = true)
    public Ordine ordine;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fkIdProdotto", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "righe", allowSetters = true)
    public Prodotto prodotto;

    public RigaOrdine() {

    }

    public RigaOrdine(int qta) {
        this.qta = qta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQta() {
        return qta;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public void setQta(int qta) {
        this.qta = qta;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    @Override
    public String toString() {
        return "RigaOrdine{" + "id=" + id + ", qta=" + qta + ", ordine=" + ordine + '}';
    }

}
