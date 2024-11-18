package com.example.medi.models;
// import java.util.UUID;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "contatos")
public class Contatos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com o usuário que iniciou o contato
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnoreProperties({"processosAbertos","processosConcluidos","processosEncerrados","contatos","senha","cpf","tribunalAtuacao","cidadeAtuacao","titulacaoGraduacao",
    "processosRecebidos","processosEmAnalise","processosFinalizados","mensagensEnviadas","mensagensRecebidas"})
    private ParticipanteBase usuario;

    // Relacionamento com o destinatário do contato
    @ManyToOne
    @JoinColumn(name = "contato_id", nullable = false)
    @JsonIgnoreProperties({"processosAbertos","processosConcluidos","processosEncerrados","contatos","senha","cpf","tribunalAtuacao","cidadeAtuacao","titulacaoGraduacao",
    "processosRecebidos","processosEmAnalise","processosFinalizados","mensagensEnviadas","mensagensRecebidas"})
    private ParticipanteBase contato;
}