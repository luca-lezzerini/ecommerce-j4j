/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.model;

import java.util.List;

/**
 *
 * @author utente
 */
public class TagliaColoriResponseDto {
    private List <Colori> listaColori;
    private List <Colori> listaColoriAssociati;

    public TagliaColoriResponseDto(List<Colori> listaColori, List<Colori> listaColoriAssociati) {
        this.listaColori = listaColori;
        this.listaColoriAssociati = listaColoriAssociati;
    }

    public TagliaColoriResponseDto() {
    }

    public List <Colori> getListaColori() {
        return listaColori;
    }

    public void setListaColori(List <Colori> listaColori) {
        this.listaColori = listaColori;
    }

    public List <Colori> getListaColoriAssociati() {
        return listaColoriAssociati;
    }

    public void setListaColoriAssociati(List <Colori> listaColoriAssociati) {
        this.listaColoriAssociati = listaColoriAssociati;
    }
    
    
}
