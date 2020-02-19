/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.model;

// creo il data transfer object della cancellazione delle spedizioni
public class SpedizioneDeleteDto extends LoginResponseDto {

    Long idToDelete;

    public SpedizioneDeleteDto(Long idToDelete, String token) {
        super(token);
        this.idToDelete = idToDelete;
       
    }

    public SpedizioneDeleteDto() {
    }

    // metto i getter e i setter
    public Long getIdToDelete() {
        return idToDelete;
    }

    public void setIdToDelete(Long idToDelete) {
        this.idToDelete = idToDelete;
    }

}
