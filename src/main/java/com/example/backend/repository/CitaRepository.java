package com.example.backend.repository;

import com.example.backend.model.Cita;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface CitaRepository extends MongoRepository<Cita, String> {
    List<Cita> findByEspecialistaId(String especialistaId);
}

