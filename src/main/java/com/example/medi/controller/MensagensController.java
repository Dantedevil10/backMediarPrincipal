package com.example.medi.controller;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medi.dto.MensagensDTO;
import com.example.medi.models.Mensagens;
import com.example.medi.repository.MensagensRepository;
import com.example.medi.services.MensagensService;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/mensagens")
@CrossOrigin(origins = "http://localhost:4200")
public class MensagensController {

    @Autowired
    private MensagensService mensagensService;
    @Autowired
    private MensagensRepository menRepo;

    @GetMapping
    public List<Mensagens> listaMensagens(){
        return menRepo.findAll();
    }

    // Método para listar mensagens entre o usuário e o contato
    @GetMapping("/conversa/{remetenteId}/{destinatarioId}")
    public List<Mensagens> listaMensagens(@PathVariable UUID remetenteId, @PathVariable UUID destinatarioId) {
        // Busca as mensagens onde o remetente é o usuário e o destinatário é o contato
        List<Mensagens> mensagensDeRemetenteParaDestinatario = menRepo.findByRemetenteIdAndDestinatarioId(remetenteId, destinatarioId);
        // Busca as mensagens onde o remetente é o contato e o destinatário é o usuário
        List<Mensagens> mensagensDeDestinatarioParaRemetente = menRepo.findByRemetenteIdAndDestinatarioId(destinatarioId, remetenteId);
        
        // Junta as duas listas
        mensagensDeRemetenteParaDestinatario.addAll(mensagensDeDestinatarioParaRemetente);
        
        // Retorna todas as mensagens ordenadas (opcional, você pode ordenar por data de envio)
        mensagensDeRemetenteParaDestinatario.sort(Comparator.comparing(Mensagens::getDataEnvio));  // Supondo que 'dataEnvio' seja a data de envio da mensagem
        return mensagensDeRemetenteParaDestinatario;
    }

    @PostMapping("/enviar")
    public Mensagens enviarMensagem(@RequestBody MensagensDTO mensagemDto) {
       return mensagensService.enviarMensagem(mensagemDto);
    }
    
}
