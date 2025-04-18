package com.example.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Document(collection = "citas")
public class Cita {

    @Id
    private String id;

    @DBRef
    private Usuario especialista;

    @DBRef
    private Usuario paciente;

    private LocalDate fecha;
    private LocalTime hora;
    private String ubicacion;

    public Cita(Usuario especialista, Usuario paciente, LocalDate fecha, LocalTime hora, String ubicacion) {
        this.especialista = especialista;
        this.paciente = paciente;
        this.fecha = fecha;
        this.hora = hora;
        this.ubicacion = ubicacion;
    }
}
