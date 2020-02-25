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
public class ProdottoTagliaResponseDto {
    private List<Taglia> taglieDisponibili;
    private List<Taglia> taglieNonDisponibili;

    public ProdottoTagliaResponseDto(List<Taglia> taglieDisponibili, List<Taglia> taglieNonDisponibili) {
        this.taglieDisponibili = taglieDisponibili;
        this.taglieNonDisponibili = taglieNonDisponibili;
    }

    public ProdottoTagliaResponseDto() {
    }

    public List<Taglia> getTaglieDisponibili() {
        return taglieDisponibili;
    }

    public void setTaglieDisponibili(List<Taglia> taglieDisponibili) {
        this.taglieDisponibili = taglieDisponibili;
    }

    public List<Taglia> getTaglieNonDisponibili() {
        return taglieNonDisponibili;
    }

    public void setTaglieNonDisponibili(List<Taglia> taglieNonDisponibili) {
        this.taglieNonDisponibili = taglieNonDisponibili;
    }
}
