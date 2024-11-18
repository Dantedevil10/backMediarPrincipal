package com.example.medi.models;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;



@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Ou outro tipo de herança conforme necessário
public abstract class ParticipanteBase {
    @Id
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public abstract String getEmail();
    public abstract String getNomeUsuario();
}
