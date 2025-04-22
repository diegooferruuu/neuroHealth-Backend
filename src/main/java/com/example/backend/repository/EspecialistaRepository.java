package com.example.backend.repository;

import com.example.backend.model.Especialista;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialistaRepository extends MongoRepository<Especialista, String> {
}