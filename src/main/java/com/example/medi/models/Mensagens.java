package com.example.medi.models;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "mensagens_usuarios")
public class Mensagens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com o remetente (usuário que envia a mensagem)
    @ManyToOne
    @JoinColumn(name = "remetente_id", nullable = false)
    @JsonIgnoreProperties({"mensagensEnviadas","contatos", "mensagensRecebidas", "email","senha","tribunalAtuacao",
    "cidadeAtuacao","titulacaoGraduacao","processosRecebidos","processosEmAnalise","processosFinalizados","id",
    "cpf","processosAbertos","processosConcluidos","processosEncerrados"})
    private ParticipanteBase remetente;

    // Relacionamento com o destinatário (usuário que recebe a mensagem)
    @ManyToOne
    @JoinColumn(name = "destinatario_id", nullable = false)
    @JsonIgnoreProperties({"mensagensEnviadas","contatos", "mensagensRecebidas", "email","senha","tribunalAtuacao",
    "cidadeAtuacao","titulacaoGraduacao","processosRecebidos","processosEmAnalise","processosFinalizados",
    "cpf","processosAbertos","processosConcluidos","processosEncerrados"})
    private ParticipanteBase destinatario;

    // Conteúdo da mensagem
    @Column(nullable = false, length = 1000)
    private String conteudo;

    // Data e hora de envio da mensagem
    @Column(nullable = false)
    private LocalDateTime dataEnvio;
}
