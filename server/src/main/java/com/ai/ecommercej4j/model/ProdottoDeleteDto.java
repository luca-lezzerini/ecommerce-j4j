/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.model;

/**
 *
 * @author utente
 */
public class ProdottoDeleteDto extends LoginResponseDto {

    private Long idToDelete;

    public ProdottoDeleteDto() {
    }

    public ProdottoDeleteDto(Long idToDelete, String token) {
        super(token);
        this.idToDelete = idToDelete;
    }

    public Long getIdToDelete() {
        return idToDelete;
    }

    public void setIdToDelete(Long idToDelete) {
        this.idToDelete = idToDelete;
    }

}
