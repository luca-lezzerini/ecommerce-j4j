/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.model;

import java.util.logging.Logger;

/**
 *
 * @author utente
 */
public class OrdineDeleteDto extends LoginResponseDto {
    private Long idToDelete;

    public OrdineDeleteDto(Long idToDelete) {
        this.idToDelete = idToDelete;
    }

    public OrdineDeleteDto() {
    }

    public Long getIdToDelete() {
        return idToDelete;
    }

    public void setIdToDelete(Long idToDelete) {
        this.idToDelete = idToDelete;
    }
    
    
    
}
