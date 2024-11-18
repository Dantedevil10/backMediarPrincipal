package com.example.medi.services;

import com.example.medi.dto.MensagensDTO;
import com.example.medi.models.Mensagens;
import com.example.medi.models.ParticipanteBase;
import com.example.medi.models.Usuarios;
import com.example.medi.repository.ContatosRepository;
import com.example.medi.repository.MediadoresRepository;
import com.example.medi.repository.MensagensRepository;
import com.example.medi.repository.UsuariosRepository;
import com.example.medi.models.Contatos;
import com.example.medi.models.Mediadores;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MensagensService {

    @Autowired
    private MensagensRepository mensagensRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private MediadoresRepository mediadorRepository;

    @Autowired
    private ContatosRepository contatosRepository;

    public Mensagens enviarMensagem(MensagensDTO mensagensDTO) {
        // Determina se o remetente é um usuário ou um mediador
        ParticipanteBase remetente = buscarUsuarioOuMediador(mensagensDTO.getRemetente(), true);
    
        // Determina se o destinatário é um usuário ou um mediador
        ParticipanteBase destinatario = buscarUsuarioOuMediador(mensagensDTO.getDestinatario(), false);
    
        // Criar nova mensagem
        Mensagens mensagem = new Mensagens();
        mensagem.setRemetente(remetente);
        mensagem.setDestinatario(destinatario);
        mensagem.setConteudo(mensagensDTO.getConteudo());
        mensagem.setDataEnvio(LocalDateTime.now());
    
        // Persistir a mensagem no banco de dados
        Mensagens mensagemSalva = mensagensRepository.save(mensagem);
    
        // Adicionar o remetente como contato do destinatário se não existir
        if (contatosRepository.findByUsuarioAndContato(destinatario, remetente).isEmpty()) {
            Contatos contatoDestinatario = new Contatos();
            contatoDestinatario.setUsuario(destinatario);
            contatoDestinatario.setContato(remetente);
            contatosRepository.save(contatoDestinatario);
        }
    
        // Adicionar o destinatário como contato do remetente se não existir
        if (contatosRepository.findByUsuarioAndContato(remetente, destinatario).isEmpty()) {
            Contatos contatoRemetente = new Contatos();
            contatoRemetente.setUsuario(remetente);
            contatoRemetente.setContato(destinatario);
            contatosRepository.save(contatoRemetente);
        }
    
        return mensagemSalva;
    }

    private ParticipanteBase buscarUsuarioOuMediador(UUID id, boolean isRemetente) {
        // Busca um usuário
        Usuarios usuario = usuariosRepository.findById(id).orElse(null);
        if (usuario != null) {
            return usuario;
        }

        // Se não encontrou um usuário, busca um mediador
        Mediadores mediador = mediadorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuário ou Mediador não encontrado"));

        // Retorna o mediador encontrado
        return mediador;
    }

    public ParticipanteBase encontrarParticipante(UUID id) { //função para o controller do Contatos
        return buscarUsuarioOuMediador(id, true);
    }
}
