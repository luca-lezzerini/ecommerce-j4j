/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.model;


import javax.persistence.*;

/**
 *
 * @author utente
 */
public class ProdottoTaglia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    
    @ManyToOne
    @JoinColumn (name = "prodotto_id")
    Prodotto prodotto;
    
    @ManyToOne
    @JoinColumn (name = "taglia_id")
    Taglia taglia;
}
