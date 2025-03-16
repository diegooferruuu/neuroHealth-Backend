package com.example.backend.controller;

import com.example.backend.model.Usuario;
import com.example.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

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
    public Usuario registrarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.registrarUsuario(usuario);
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

