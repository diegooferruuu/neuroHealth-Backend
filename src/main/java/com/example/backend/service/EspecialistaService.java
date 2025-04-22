package com.example.backend.service;

import com.example.backend.model.Especialista;
import com.example.backend.repository.EspecialistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

@Service
public class EspecialistaService {

    @Autowired
    private EspecialistaRepository especialistaRepository;

    // Obtener todos los especialistas
    public List<Especialista> getAllEspecialistas() {
        return especialistaRepository.findAll();
    }

    // Obtener horarios de un especialista por ID
    public Map<String, List<String>> getHorariosByEspecialistaId(String id) {
        Optional<Especialista> especialista = especialistaRepository.findById(id);
        Map<String, List<String>> horarios = new HashMap<>();

        if (especialista.isPresent()) {
            horarios.put("hours", especialista.get().getHours());
            horarios.put("occupiedHours", especialista.get().getOccupiedHours());
        }

        return horarios;
    }
}