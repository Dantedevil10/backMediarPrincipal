package com.example.medi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.example.medi.dto.ProcessosDTO;

import com.example.medi.models.Processos;
import com.example.medi.repository.ProcessosRepository;
import com.example.medi.services.ProcessosService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/processos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProcessosController {

    @Autowired
    private ProcessosService processService;

    @Autowired
    private ProcessosRepository processRepository;

    //GETS
    @GetMapping //esse é o get para mostrar todos 
    public List<Processos> listaProcessos(){
        return processRepository.findAll();
    }
    @GetMapping("/{id}") //esse é o get para mostrar o usuario pelo seu ID Unico
    public ResponseEntity<Processos> findbyid(@PathVariable UUID id){
        
        return processRepository.findById(id)
        .map(recordFound -> ResponseEntity.ok().body(recordFound))
        .orElse(ResponseEntity.notFound().build());
    }

    //POST
    @PostMapping("/criarProcesso")
    public Processos criarProcesso(@RequestBody ProcessosDTO ProDto) {
        // Passe o criadorId para o serviço ao criar o processo
        return processService.criarProcesso(ProDto);
    }

    //PUT - Este campo edita somente o status do processo
    @PutMapping("/editarStatus/{id}")
    public ResponseEntity<Processos> editarStatus(@PathVariable UUID id ,@RequestBody  ProcessosDTO ProDto){
        try{
            Processos updateStatus = processService.editarProcesso(id, ProDto);

            return ResponseEntity.ok(updateStatus);

        }catch(RuntimeException e){
            // Soltar uma excessao se o usuario nao for encontrado ou essa caceta der problema
            return ResponseEntity.notFound().build();
        }
    }
    
}
