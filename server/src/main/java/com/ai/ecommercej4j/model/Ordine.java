package com.ai.ecommercej4j.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private LocalDate data;
    @Column
    private int numero;
    @Column
    private String stato;

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    @OneToMany(mappedBy = "ordine", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = "ordine", allowSetters = true)
    List<RigaOrdine> righe = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fkIdUtente", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "ordini", allowSetters = true)
    private Utente utente;

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Ordine() {
    }

    /**
     * Inizializza un nuovo ordine partendo dallo stato carrello.
     *
     * @param utente L'utente associato all'ordine da creare
     */
    public Ordine(Utente utente) {
        this.data = LocalDate.now();
        this.stato = "carrello";
        this.numero = (int) (Math.random() * 10000 + 1);
        this.utente = utente;
    }

    public Ordine(LocalDate data, int numero, String stato, Utente utente) {
        this.data = data;
        this.numero = numero;
        this.stato = stato;
        this.utente = utente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public List<RigaOrdine> getRighe() {
        return righe;
    }

}
