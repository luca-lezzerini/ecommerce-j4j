package com.ai.ecommercej4j.model;

public class RegistrazioneResponseDto {

    boolean registrato;
    String messaggio;

    public boolean isRegistrato() {
        return registrato;
    }

    public void setRegistrato(boolean registrato) {
        this.registrato = registrato;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public RegistrazioneResponseDto(boolean registrato, String messaggio) {
        this.registrato = registrato;
        this.messaggio = messaggio;
    }

    public RegistrazioneResponseDto() {
    }
}
