package com.example.medi.repository;

import com.example.medi.models.Contatos;
import com.example.medi.models.ParticipanteBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ContatosRepository extends JpaRepository<Contatos, Long> {
    
    // Método para buscar um contato pelo usuário e pelo contato
    Optional<Contatos> findByUsuarioAndContato(ParticipanteBase usuario, ParticipanteBase contato);

    // Método para buscar contatos por usuário
    List<Contatos> findByUsuario(ParticipanteBase usuario);
}