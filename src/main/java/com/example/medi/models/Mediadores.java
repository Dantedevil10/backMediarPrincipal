package com.example.medi.models;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "mediadores")
public class Mediadores extends ParticipanteBase {
    @Id
    private UUID id;

    public Mediadores() {
        this.id = UUID.randomUUID();
    }

    // Em Usuarios e Mediadores (ambos são ParticipanteBase)
    @OneToMany(mappedBy = "usuario")
    @JsonIgnoreProperties("usuario")
    private List<Contatos> contatos;


    @Column(nullable = false)
    private String senha;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    
    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 20)
    private String nomeUsuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TribunalAtuacao tribunalAtuacao;

    @Column(nullable = false)
    private String cidadeAtuacao;

    @Column(nullable = false)
    private String titulacaoGraduacao;

    @Column(nullable = false)
    private String tipoDeConta = "MEDIADOR";

    @OneToMany(mappedBy = "mediadorEscolhido")
    @JsonIgnoreProperties("mediadorEscolhido")
    private List<Processos> processosRecebidos;

    @OneToMany(mappedBy = "remetente")
    @JsonIgnoreProperties("remetente")
    private List<Mensagens> mensagensEnviadas;

    @OneToMany(mappedBy = "destinatario")
    @JsonIgnoreProperties("destinatario")
    private List<Mensagens> mensagensRecebidas;

    public enum TribunalAtuacao {
        TODOS,
        ESTADUAL,
        FEDERAL
    }
}
