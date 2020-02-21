package com.ai.ecommercej4j.model;

import java.util.List;

public class ViewCarrelloResponseDto {

    private List<RigaOrdine> carrello;
    private Ordine ordine;
    private double totale;
    
    public ViewCarrelloResponseDto() {
    }

    public ViewCarrelloResponseDto(List<RigaOrdine> carrello, Ordine ordine, double totale) {
        this.carrello = carrello;
        this.ordine = ordine;
        this.totale = totale;
    }

    public List<RigaOrdine> getCarrello() {
        return carrello;
    }

    public void setCarrello(List<RigaOrdine> carrello) {
        this.carrello = carrello;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }
    
}
