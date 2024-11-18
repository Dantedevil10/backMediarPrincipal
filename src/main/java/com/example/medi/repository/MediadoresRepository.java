package com.example.medi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

import com.example.medi.models.Mediadores;

public interface MediadoresRepository extends JpaRepository<Mediadores,UUID> {
    boolean existsByEmail(String email);
    boolean existsByNomeUsuario(String nomeUsuario);
    Mediadores findByCpfAndSenha(String cpf, String senha);
}
