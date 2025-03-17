package com.example.backend.controller;

import com.example.backend.model.Usuario;
import com.example.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.obtenerTodos();
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody Map<String, String> usuarioData) {
        try {
            String nombre = usuarioData.get("nombre");
            String apellido = usuarioData.get("apellido");
            String email = usuarioData.get("email");
            String contrasena = usuarioData.get("contrasena");
            String rol = usuarioData.getOrDefault("rol", "usuario");

            Usuario nuevoUsuario = usuarioService.registrarUsuario(nombre, apellido, email, contrasena, rol);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<Usuario> iniciarSesion(@RequestParam String email, @RequestParam String contrasena) {
        Usuario user = usuarioService.iniciarSesion(email, contrasena);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Retorna un 401 si no se encuentra el usuario
        }

        return ResponseEntity.ok(user); // Retorna el usuario si es válido
    }

}

