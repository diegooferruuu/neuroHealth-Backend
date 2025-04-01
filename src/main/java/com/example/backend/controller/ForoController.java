package com.example.backend.controller;

import com.example.backend.model.Comentario;
import com.example.backend.model.Publicacion;
import com.example.backend.model.Usuario;
import com.example.backend.service.ForoService;
import com.example.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/foro")
public class ForoController {

    @Autowired
    private ForoService foroService;

    @Autowired
    private UsuarioService usuarioService;

    // Endpoints para Publicaciones
    @PostMapping("/publicaciones")
    public ResponseEntity<?> crearPublicacion(
            @RequestParam String usuarioId,
            @RequestParam String titulo,
            @RequestParam String contenido,
            @RequestParam String tema) {

        Usuario usuario = usuarioService.obtenerUsuarioPorId(usuarioId);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado con ID: " + usuarioId);
        }

        try {
            Publicacion publicacion = foroService.crearPublicacion(usuario, titulo, contenido, tema);
            return ResponseEntity.ok(publicacion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear publicación: " + e.getMessage());
        }
    }

    @GetMapping("/publicaciones")
    public ResponseEntity<List<Map<String, Object>>> obtenerTodasPublicaciones() {
        return ResponseEntity.ok(foroService.obtenerTodasPublicaciones());
    }

}

