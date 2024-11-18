// AuthController.java
package com.example.medi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.medi.dto.Login.LoginRequestDTO;
import com.example.medi.dto.Login.LoginResponseDTO;
import com.example.medi.models.Usuarios;
import com.example.medi.models.Mediadores;
import com.example.medi.repository.UsuariosRepository;
import com.example.medi.repository.MediadoresRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private MediadoresRepository mediadoresRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        // Primeiro verifica se é um Usuário comum
        Usuarios usuario = usuariosRepository.findByCpfAndSenha(loginRequest.getCpf(), loginRequest.getSenha());
        if (usuario != null) {
            return ResponseEntity.ok(new LoginResponseDTO(usuario.getId(), usuario.getTipoDeConta()));
        }

        // Se não for um usuário comum, verifica se é um Mediador
        Mediadores mediador = mediadoresRepository.findByCpfAndSenha(loginRequest.getCpf(), loginRequest.getSenha());
        if (mediador != null) {
            return ResponseEntity.ok(new LoginResponseDTO(mediador.getId(), mediador.getTipoDeConta()));
        }

        // Caso nenhum seja encontrado, retorna erro 401
        return ResponseEntity.status(401).body("Credenciais inválidas");
    }
}
