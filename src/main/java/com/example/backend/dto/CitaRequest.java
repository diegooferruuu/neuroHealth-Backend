package com.example.backend.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CitaRequest {
    private String idEspecialista;
    private String idPaciente;
    private LocalDate fecha;
    private LocalTime hora;
    private String ubicacion;
}
