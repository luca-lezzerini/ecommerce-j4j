package com.ai.ecommercej4j.model;

import java.util.List;

public class ViewCarrelloResponseDto {

    private List<RigaOrdine> carrello;
    
    public ViewCarrelloResponseDto() {
    }

    public ViewCarrelloResponseDto(List<RigaOrdine> carrello) {
        this.carrello = carrello;
    }

    public List<RigaOrdine> getCarrello() {
        return carrello;
    }

    public void setCarrello(List<RigaOrdine> carrello) {
        this.carrello = carrello;
    }
    
}
