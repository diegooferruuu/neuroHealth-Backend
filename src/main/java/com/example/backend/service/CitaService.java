package com.example.backend.service;

import com.example.backend.dto.CitaRequest;
import com.example.backend.model.Cita;
import com.example.backend.model.Usuario;
import com.example.backend.repository.CitaRepository;
import com.example.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CitaRepository citaRepository;

    public Cita agendarCita(CitaRequest request) {
        Usuario especialista = usuarioRepository.findById(request.getIdEspecialista()).orElse(null);
        Usuario paciente = usuarioRepository.findById(request.getIdPaciente()).orElse(null);

        if (especialista == null || paciente == null) {
            throw new RuntimeException("Especialista o paciente no encontrados.");
        }

        Cita nuevaCita = new Cita();
        nuevaCita.setEspecialista(especialista);
        nuevaCita.setPaciente(paciente);
        nuevaCita.setFecha(request.getFecha());
        nuevaCita.setHora(request.getHora());
        nuevaCita.setUbicacion(request.getUbicacion());

        return citaRepository.save(nuevaCita);
    }
}
