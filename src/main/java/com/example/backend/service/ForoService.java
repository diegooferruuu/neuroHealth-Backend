package com.example.backend.service;

import com.example.backend.model.Comentario;
import com.example.backend.model.Publicacion;
import com.example.backend.model.Usuario;
import com.example.backend.repository.ComentarioRepository;
import com.example.backend.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ForoService {

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioService usuarioService;


    public Publicacion crearPublicacion(Usuario usuario, String titulo, String contenido, String tema) {
        Publicacion publicacion = new Publicacion(usuario, titulo, contenido, tema);
        return publicacionRepository.save(publicacion);
    }

    public List<Publicacion> obtenerTodasPublicaciones() {
        return publicacionRepository.findByOrderByFechaPublicacionDesc();
    }

    public Publicacion obtenerPublicacionPorId(String id) {
        return publicacionRepository.findById(id).orElse(null);
    }


    public Comentario crearComentario(Publicacion publicacion, Usuario usuario, String contenido, String comentarioPadreId) {
        Comentario comentario = new Comentario(publicacion, usuario, contenido, comentarioPadreId);
        Comentario comentarioGuardado = comentarioRepository.save(comentario);

        if (comentarioPadreId == null) {
            publicacion.getComentarioIds().add(comentarioGuardado.getId());
            publicacionRepository.save(publicacion);
        }

        return comentarioGuardado;
    }

}