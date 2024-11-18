package com.example.medi.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medi.models.Contatos;
import com.example.medi.models.ParticipanteBase;
import com.example.medi.repository.ContatosRepository;
import com.example.medi.services.MensagensService;
import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatosController {

    @Autowired
    private ContatosRepository contatosRepository;

    @Autowired
    private MensagensService mensagensService;

    // Endpoint para obter os contatos de um usuário ou mediador
    @GetMapping("/{id}")
    public ResponseEntity<List<Contatos>> obterContatos(@PathVariable UUID id) {
        // Verifica se o usuário ou mediador existe
        ParticipanteBase participante = mensagensService.encontrarParticipante(id);
        
        // Busca os contatos associados a esse usuário ou mediador
        List<Contatos> contatos = contatosRepository.findByUsuario(participante);
        
        return ResponseEntity.ok(contatos);
    }
    
}
