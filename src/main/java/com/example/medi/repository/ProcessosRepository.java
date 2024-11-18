package com.example.medi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medi.models.Processos;
import java.util.UUID;

public interface ProcessosRepository extends JpaRepository<Processos,UUID>{
    
}
