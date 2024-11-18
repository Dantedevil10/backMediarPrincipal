package com.example.medi.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "processos")
public class Processos {
    @Id
    @Column(length = 20, nullable = false, unique = true)
    private UUID id;

    // Construtor para gerar um novo ID
    public Processos() {
        this.id = UUID.randomUUID();
    }


    // Relacionamento com Usuário Comum
    @ManyToOne
    @JoinColumn(name = "criador_id", nullable = false)
    @JsonIgnoreProperties({"processos","mensagensEnviadas","contatos", "mensagensRecebidas", "email","senha","tribunalAtuacao",
    "cidadeAtuacao","titulacaoGraduacao","processosRecebidos","processosEmAnalise","processosFinalizados",
    "cpf","processosAbertos","processosConcluidos","processosEncerrados"})
    private Usuarios criador;  // Usuário comum que iniciou o processo

    // Relacionamento com Mediador
    @ManyToOne
    @JoinColumn(name = "mediador_id")
    @JsonIgnoreProperties({"processos","mensagensEnviadas","contatos", "mensagensRecebidas", "email","senha","tribunalAtuacao",
    "cidadeAtuacao","titulacaoGraduacao","processosRecebidos","processosEmAnalise","processosFinalizados",
    "cpf","processosAbertos","processosConcluidos","processosEncerrados"})
    private Mediadores mediadorEscolhido;  // Mediador que analisará o processo

    // Status do processo
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusProcesso status;

    @Column(nullable = false)
    private String nomeAutor;

    @Column(nullable = false)
    private String nomeReu;

    @Column(nullable = false, length = 11)
    private String cpfAutor;

    @Column(nullable = false, length = 255)
    private String dadosAcusado;

    @Column(nullable = false, length = 500)
    private String descricaoAcusacao;

    @Column
    private String comprovanteResidencia;

    @Column(nullable = false)
    private Boolean urgencia;

    @Column(nullable = false)
    private LocalDateTime dataEmissao;

    // Campo de email com visibilidade restrita ao Mediador
    @Column(nullable = false)
    private String emailAutor;

    public enum StatusProcesso {
        EM_ANALISE,
        ABERTO,
        FINALIZADO,
        ENCERRADO
    }

    // Campo adicional para motivo do encerramento
    @Column(length = 255)
    private String motivoEncerramento;
}
