package com.ai.ecommercej4j.model;

public class ColoriDeleteDto extends LoginResponseDto{
   private Long idToDelete; 

    public ColoriDeleteDto(Long idToDelete) {
        this.idToDelete = idToDelete;
    }

    public ColoriDeleteDto() {
    }

    public Long getIdToDelete() {
        return idToDelete;
    }

    public void setIdToDelete(Long idToDelete) {
        this.idToDelete = idToDelete;
    }
}
