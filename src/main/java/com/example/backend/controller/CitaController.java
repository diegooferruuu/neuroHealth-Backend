package com.example.backend.controller;

import com.example.backend.dto.CitaRequest;
import com.example.backend.model.Cita;
import com.example.backend.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @PostMapping("/agendar")
    public ResponseEntity<?> agendarCita(@RequestBody CitaRequest request) {
        try {
            Cita cita = citaService.agendarCita(request);
            return ResponseEntity.ok(cita);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
