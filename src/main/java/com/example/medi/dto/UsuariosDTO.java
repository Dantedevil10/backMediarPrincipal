package com.example.medi.dto;

public class UsuariosDTO {

    private String email;
    private String cpf;
    private String senha;
    private String nomeUsuario;


    
    public UsuariosDTO(String nome, String email, String cpf, String senha, String nomeUsuario) {
        
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.nomeUsuario = nomeUsuario;
    }
    
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getNomeUsuario() {
        return nomeUsuario;
    }
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    
}
