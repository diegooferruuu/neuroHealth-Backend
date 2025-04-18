package com.example.backend.service;

import com.example.backend.dto.CitaDTO;
import com.example.backend.model.Cita;
import com.example.backend.repository.CitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
}
