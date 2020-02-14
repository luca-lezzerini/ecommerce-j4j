/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.model;

public class SpedizioneDeleteDto extends LoginResponseDto {

    Long idToDelete;

    public SpedizioneDeleteDto(Long idToDelete, String token) {
        super(token);
        this.idToDelete = idToDelete;
       
    }

    public SpedizioneDeleteDto() {
    }

    public Long getIdToDelete() {
        return idToDelete;
    }

    public void setIdToDelete(Long idToDelete) {
        this.idToDelete = idToDelete;
    }

}
