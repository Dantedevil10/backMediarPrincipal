package com.example.medi.dto;
import java.util.UUID;

import java.time.LocalDateTime;

public class MensagensDTO {
    private Long id;
    private UUID remetente;
    private UUID destinatario;
    private String conteudo;
    private LocalDateTime dataEnvio;

    
    public MensagensDTO(Long id, UUID remetente, UUID destinatario, String conteudo, LocalDateTime dataEnvio) {
        this.id = id;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.conteudo = conteudo;
        this.dataEnvio = dataEnvio;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public UUID getRemetente() {
        return remetente;
    }
    public void setRemetente(UUID remetente) {
        this.remetente = remetente;
    }
    public UUID getDestinatario() {
        return destinatario;
    }
    public void setDestinatario(UUID destinatario) {
        this.destinatario = destinatario;
    }
    public String getConteudo() {
        return conteudo;
    }
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }
    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }


    
}
