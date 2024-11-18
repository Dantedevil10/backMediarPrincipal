package com.example.medi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


import com.example.medi.dto.MediadoresDTO;
import com.example.medi.models.Mediadores;

import com.example.medi.repository.MediadoresRepository;
import com.example.medi.repository.MensagensRepository;

@Service
public class MediadoresService {
    
    @Autowired
    private MediadoresRepository mediadoresRepository;

    @Autowired
    private MensagensRepository mensagensRepository;

    public Mediadores criarMediador( MediadoresDTO mediadorDTO ){

        if(mediadoresRepository.existsByEmail(mediadorDTO.getEmail())){
            throw new IllegalArgumentException("Email já cadastrado. Escolha outro email.");
        }
        if(mediadoresRepository.existsByNomeUsuario(mediadorDTO.getNomeUsuario())){
            throw new IllegalArgumentException("Nome De Usuario já Existe. Escolha outro Nome.");
        }

        Mediadores mediador = new Mediadores();
        mediador.setEmail(mediadorDTO.getEmail());
        mediador.setCpf(mediadorDTO.getCpf());
        mediador.setNomeUsuario(mediadorDTO.getNomeUsuario());
        mediador.setSenha(mediadorDTO.getSenha());
        mediador.setTribunalAtuacao(mediadorDTO.getTribunalAtuacao());
        mediador.setCidadeAtuacao(mediadorDTO.getCidadeAtuacao());
        mediador.setTitulacaoGraduacao(mediadorDTO.getTitulacaoGraduacao());

        return mediadoresRepository.save(mediador);
    }

    public Mediadores editarMediador( UUID id, MediadoresDTO mediadorDTO ){
        //Busca Usuario Pelo Id
        Mediadores mediador = mediadoresRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado para o ID: " + id));

        mediador.setEmail(mediadorDTO.getEmail());
        mediador.setNomeUsuario(mediadorDTO.getNomeUsuario());
        mediador.setSenha(mediadorDTO.getSenha());
        mediador.setTribunalAtuacao(mediadorDTO.getTribunalAtuacao());
        mediador.setCidadeAtuacao(mediadorDTO.getCidadeAtuacao());
        mediador.setTitulacaoGraduacao(mediadorDTO.getTitulacaoGraduacao());
        
        
        return mediadoresRepository.save(mediador);
    }

    @Transactional
    public Mediadores deletarMediador(UUID id){
        // Verifica se o usuário existe
        Mediadores mediador = mediadoresRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado para o ID: " + id));

        // Excluir todas as mensagens associadas ao usuário
        mensagensRepository.deleteByUsuario(mediador);

        // Excluir o mediador do repositório
        mediadoresRepository.delete(mediador);

        return mediador;
    }

}
