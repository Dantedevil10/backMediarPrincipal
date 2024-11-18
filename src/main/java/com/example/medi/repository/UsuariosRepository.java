package com.example.medi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medi.models.Usuarios;
import java.util.UUID;

public interface UsuariosRepository extends JpaRepository<Usuarios,UUID> {
    boolean existsByEmail(String email);
    boolean existsByNomeUsuario(String nomeUsuario);
    Usuarios findByCpfAndSenha(String cpf, String senha);
}