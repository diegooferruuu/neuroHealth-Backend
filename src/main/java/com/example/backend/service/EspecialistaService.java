package com.example.backend.service;

import com.example.backend.dto.CitaDTO;
import com.example.backend.model.Cita;
import com.example.backend.repository.CitaRepository;
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

    private final CitaRepository citaRepository;

    public EspecialistaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public List<CitaDTO> obtenerCitasPorEspecialista(String especialistaId) {
        List<Cita> citas = citaRepository.findByEspecialistaId(especialistaId);

        return citas.stream().map(cita -> new CitaDTO(
                cita.getEspecialista().getId(),
                cita.getEspecialista().getNombre() + " " + cita.getEspecialista().getApellido(),
                cita.getPaciente().getId(),
                cita.getFecha().toString(),
                cita.getHora().toString(),
                cita.getUbicacion()
        )).collect(Collectors.toList());
    }
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

    public boolean addOccupiedHour(String id, String hour) {
        Optional<Especialista> especialistaOpt = especialistaRepository.findById(id);
        if (especialistaOpt.isPresent()) {
            Especialista especialista = especialistaOpt.get();
            List<String> occupied = especialista.getOccupiedHours();
            if (!occupied.contains(hour)) {
                occupied.add(hour);
                especialista.setOccupiedHours(occupied);
                especialistaRepository.save(especialista);
                return true;
            }
        }
        return false;
    }

}
