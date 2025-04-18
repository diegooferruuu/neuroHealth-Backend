package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CitaDTO {
    private String idEspecialista;
    private String nombreEspecialista;
    private String idPaciente;
    private String fecha;
    private String hora;
    private String ubicacion;
}
