package com.example.medi.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medi.dto.MediadoresDTO;
import com.example.medi.models.Mediadores;

import com.example.medi.repository.MediadoresRepository;

import com.example.medi.services.MediadoresService;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/userMedi")
@CrossOrigin(origins = "http://localhost:4200")
public class MediadoresController {

    @Autowired
    private MediadoresService mediadoresService;

    @Autowired
    private MediadoresRepository mediadoresRepository;


    //GETS
    @GetMapping //esse é o get para mostrar todos os usuários
    public List<Mediadores> listaMediadores(){
        return mediadoresRepository.findAll();
    }
    @GetMapping("/{id}") //esse é o get para mostrar o usuario pelo seu ID Unico
    public ResponseEntity<Mediadores> findbyid(@PathVariable UUID id){
        
        return mediadoresRepository.findById(id)
        .map(recordFound -> ResponseEntity.ok().body(recordFound))
        .orElse(ResponseEntity.notFound().build());
    }
    
    //POST
    @PostMapping("criarMedi")
    public Mediadores criarMediador(@RequestBody MediadoresDTO mediDto){

        return mediadoresService.criarMediador(mediDto);
    }

    //PUT
    @PutMapping("/editarMedi/{id}")
    public ResponseEntity<Mediadores> editarMediador(@PathVariable UUID id ,@RequestBody  MediadoresDTO mediadoresDto){
        try{
            Mediadores updatedMediador = mediadoresService.editarMediador(id, mediadoresDto);

            return ResponseEntity.ok(updatedMediador);

        }catch(RuntimeException e){
            // Soltar uma excessao se o usuario nao for encontrado ou essa caceta der problema
            return ResponseEntity.notFound().build();
        }
    }

    //DELETE
    @DeleteMapping("/deletarMedi/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable UUID id){
       
        try {
            mediadoresService.deletarMediador(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            // Soltar uma excessao se o usuario nao for encontrado ou essa caceta der problema
            return ResponseEntity.notFound().build();
        }
       
        
    }

}
