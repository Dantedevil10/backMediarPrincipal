package com.example.medi.dto;

import com.example.medi.models.Mediadores.TribunalAtuacao;

public class MediadoresDTO {
    
    private String id;
    public MediadoresDTO(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    private String cpf;
    private String senha;
    private String nomeUsuario;
    private String cidadeAtuacao;
    private String titulacaoGraduacao;
    private TribunalAtuacao tribunalAtuacao;
    private String email;

    
    public MediadoresDTO(String cpf, String email,String senha, String nomeUsuario, String cidadeAtuacao, String titulacaoGraduacao,
    TribunalAtuacao tribunalAtuacao) {
        this.senha = senha;
        this.cpf = cpf;
        this.email = email;
        this.nomeUsuario = nomeUsuario;
        this.cidadeAtuacao = cidadeAtuacao;
        this.titulacaoGraduacao = titulacaoGraduacao;
        this.tribunalAtuacao = tribunalAtuacao;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    public String getCidadeAtuacao() {
        return cidadeAtuacao;
    }
    public void setCidadeAtuacao(String cidadeAtuacao) {
        this.cidadeAtuacao = cidadeAtuacao;
    }
    public String getTitulacaoGraduacao() {
        return titulacaoGraduacao;
    }
    public void setTitulacaoGraduacao(String titulacaoGraduacao) {
        this.titulacaoGraduacao = titulacaoGraduacao;
    }
    public TribunalAtuacao getTribunalAtuacao() {
        return tribunalAtuacao;
    }
    public void setTribunalAtuacao(TribunalAtuacao tribunalAtuacao) {
        this.tribunalAtuacao = tribunalAtuacao;
    }
}
