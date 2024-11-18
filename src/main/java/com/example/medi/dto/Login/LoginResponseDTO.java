package com.example.medi.dto.Login;

import java.util.UUID;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private UUID id;
    private String tipoDeConta;

    public LoginResponseDTO(UUID id, String tipoDeConta) {
        this.id = id;
        this.tipoDeConta = tipoDeConta;
    }
}