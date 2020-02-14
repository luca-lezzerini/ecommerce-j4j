package com.ai.ecommercej4j.model;

public class TagliaDeleteDto extends LoginResponseDto {

    private Long idToDelete;

    public TagliaDeleteDto(Long idToDelete, String token) {
        super(token);
        this.idToDelete = idToDelete;
    }

    public TagliaDeleteDto() {
    }

    public Long getidToDelete() {
        return idToDelete;
    }

    public void setidToDelete(Long idToDelete) {
        this.idToDelete = idToDelete;
    }

}
