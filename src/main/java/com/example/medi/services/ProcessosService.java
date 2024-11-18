package com.example.medi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.medi.dto.ProcessosDTO;
import com.example.medi.models.Mediadores;
import com.example.medi.models.Processos;
import com.example.medi.models.Usuarios;
import com.example.medi.repository.MediadoresRepository;
import com.example.medi.repository.ProcessosRepository;
import com.example.medi.repository.UsuariosRepository;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;
import java.util.List;

@Service
public class ProcessosService {

    @Autowired
    private ProcessosRepository processosRepository;

    @Autowired
    private UsuariosRepository usuariosRepository; // Repositório para buscar usuários

    @Autowired
    private MediadoresRepository mediadoresRepository; // Repositório para buscar mediadores

    public Processos criarProcesso(ProcessosDTO proDTO) {
        // Busca o criador (usuário) pelo ID
        Usuarios criador = usuariosRepository.findById(proDTO.getCriadorId())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + proDTO.getCriadorId()));
    
        // Verifica se o CPF do autor no processo corresponde ao CPF do usuário
        if (!criador.getCpf().equals(proDTO.getCpfAutor())) {
            throw new RuntimeException("O CPF do autor do processo deve corresponder ao CPF do usuário.");
        }
    
        Processos processo = new Processos();
        processo.setCriador(criador);
        processo.setComprovanteResidencia(proDTO.getComprovanteResidencia());
        processo.setCpfAutor(proDTO.getCpfAutor());
        processo.setDadosAcusado(proDTO.getDadosAcusado());
        processo.setDescricaoAcusacao(proDTO.getDescricaoAcusacao());
        processo.setEmailAutor(proDTO.getEmailAutor());
        processo.setMotivoEncerramento(proDTO.getMotivoEncerramento());
        processo.setNomeAutor(proDTO.getNomeAutor());
        processo.setNomeReu(proDTO.getNomeReu());
        processo.setStatus(Processos.StatusProcesso.ABERTO);
        processo.setUrgencia(proDTO.getUrgencia());
        processo.setDataEmissao(LocalDateTime.now());
    
        // Seleção aleatória de um mediador
        List<Mediadores> mediadores = mediadoresRepository.findAll();
        if (!mediadores.isEmpty()) {
            Random random = new Random();
            Mediadores mediadorEscolhido = mediadores.get(random.nextInt(mediadores.size()));
            processo.setMediadorEscolhido(mediadorEscolhido);
        }
    
        return processosRepository.save(processo);  
    }
    

    public Processos editarProcesso(UUID id, ProcessosDTO proDTO) {
        // Busca o processo pelo ID
        Processos process = processosRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Processo não encontrado para o ID: " + id));

        Usuarios criador = process.getCriador();
        Mediadores mediadorEscolhido = process.getMediadorEscolhido();

        // Atualiza o status
        process.setStatus(proDTO.getStatus());

        if (Processos.StatusProcesso.ENCERRADO.equals(proDTO.getStatus())) {
            process.setMotivoEncerramento(proDTO.getMotivoEncerramento());
        }

        // Salva as alterações no banco de dados
        usuariosRepository.save(criador);
        if (mediadorEscolhido != null) {
            mediadoresRepository.save(mediadorEscolhido);
        }

        return processosRepository.save(process);
    }


    
}
