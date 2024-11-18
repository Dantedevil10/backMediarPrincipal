package com.example.medi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.medi.dto.UsuariosDTO;

import com.example.medi.models.Usuarios;
import com.example.medi.repository.MensagensRepository;
import com.example.medi.repository.UsuariosRepository;
import java.util.UUID;

@Service
public class UsuariosService {
    
    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private MensagensRepository mensagensRepository;


    public Usuarios criarUsuario( UsuariosDTO usuariosDTO){

        if(usuariosRepository.existsByEmail(usuariosDTO.getEmail())){
            throw new IllegalArgumentException("Email já cadastrado. Escolha outro email.");
        }
        if(usuariosRepository.existsByNomeUsuario(usuariosDTO.getNomeUsuario())){
            throw new IllegalArgumentException("Nome De Usuario já Existe. Escolha outro Nome.");
        }

        Usuarios usuario = new Usuarios();
        usuario.setCpf(usuariosDTO.getCpf());
        usuario.setEmail(usuariosDTO.getEmail());
        usuario.setNomeUsuario(usuariosDTO.getNomeUsuario());
        usuario.setSenha(usuariosDTO.getSenha());

        return usuariosRepository.save(usuario);
    }

    public Usuarios editarUsuario( UUID id, UsuariosDTO usuariosDTO ){
        //Busca Usuario Pelo Id
        Usuarios usuario = usuariosRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado para o ID: " + id));

        usuario.setCpf(usuariosDTO.getCpf());
        usuario.setEmail(usuariosDTO.getEmail());
        usuario.setNomeUsuario(usuariosDTO.getNomeUsuario());
        usuario.setSenha(usuariosDTO.getSenha());
        
        
        return usuariosRepository.save(usuario);
    }

    @Transactional
    public Usuarios deletarUsuario(UUID id){
        // Verifica se o usuário existe
        Usuarios usuario = usuariosRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado para o ID: " + id));

        // Excluir todas as mensagens associadas ao usuário
        mensagensRepository.deleteByUsuario(usuario);

        // Excluir o mediador do repositório
        usuariosRepository.delete(usuario);

        return usuario;
    }


}
