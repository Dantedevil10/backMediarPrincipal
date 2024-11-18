package com.example.medi.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.medi.dto.UsuariosDTO;

import com.example.medi.models.Usuarios;

import com.example.medi.repository.UsuariosRepository;
import com.example.medi.services.UsuariosService;

@RestController
@RequestMapping("/userCom")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuariosController {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private UsuariosService usuariosService;

    
    
    //GETS
    @GetMapping //esse é o get para mostrar todos os usuários
    public List<Usuarios> listaMediadores(){
        return usuariosRepository.findAll();
    }
    @GetMapping("/{id}") //esse é o get para mostrar o usuario pelo seu ID Unico
    public ResponseEntity<Usuarios> findbyid(@PathVariable UUID id){
        
        return usuariosRepository.findById(id)
        .map(recordFound -> ResponseEntity.ok().body(recordFound))
        .orElse(ResponseEntity.notFound().build());
    }

    //POST
    @PostMapping("criarUsuario")
    public Usuarios criarMediador(@RequestBody UsuariosDTO userDto){
        return usuariosService.criarUsuario(userDto);
    }

    //PUT
    @PutMapping("/editar/{id}")
    public ResponseEntity<Usuarios> editarUsuario(@PathVariable UUID id ,@RequestBody UsuariosDTO usuarioDto){
        try{
            Usuarios updatedUsuario = usuariosService.editarUsuario(id,usuarioDto);

            return ResponseEntity.ok(updatedUsuario);
        }catch(RuntimeException e){
            // Soltar uma excessao se o usuario nao for encontrado ou essa caceta der problema
            return ResponseEntity.notFound().build();
        }
    }

    //DELETE
    @DeleteMapping("/deletarUser/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable UUID id){
       
        try {
            usuariosService.deletarUsuario(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            // Soltar uma excessao se o usuario nao for encontrado ou essa caceta der problema
            return ResponseEntity.notFound().build();
        }
       
        
    }
}
