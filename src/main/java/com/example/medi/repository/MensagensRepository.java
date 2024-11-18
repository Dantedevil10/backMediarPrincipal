package com.example.medi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.medi.models.Mensagens;

import com.example.medi.models.ParticipanteBase;

import jakarta.transaction.Transactional;

public interface MensagensRepository extends JpaRepository<Mensagens, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Mensagens m WHERE m.remetente = :usuario OR m.destinatario = :usuario")
    void deleteByUsuario(ParticipanteBase usuario);

    List<Mensagens> findByRemetenteIdAndDestinatarioId(UUID remetenteId, UUID destinatarioId);
}
