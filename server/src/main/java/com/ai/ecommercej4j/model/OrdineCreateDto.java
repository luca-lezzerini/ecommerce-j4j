package com.ai.ecommercej4j.model;

import java.time.LocalDate;

public class OrdineCreateDto extends ProdottoCreateDto {
 
  private Long id;
  private LocalDate data;
  private int numero;
  private String stato;

    public OrdineCreateDto(Long id, LocalDate data, int numero, String stato) {
        this.id = id;
        this.data = data;
        this.numero = numero;
        this.stato = stato;
    }

    public OrdineCreateDto() {
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

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
  
  

}
