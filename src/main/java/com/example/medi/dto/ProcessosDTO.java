package com.example.medi.dto;

import java.util.UUID;
import com.example.medi.models.Processos.StatusProcesso;

import lombok.Data;

import com.example.medi.models.Usuarios;


@Data
public class ProcessosDTO {
    private UUID criadorId;
    private StatusProcesso status;
    private String nomeAutor;
    private String nomeReu;
    private String cpfAutor;
    private String dadosAcusado;
    private String descricaoAcusacao;
    private String comprovanteResidencia;
    private Boolean urgencia;
    private String emailAutor;
    private String motivoEncerramento;
    private Usuarios criador;
    
    
}
