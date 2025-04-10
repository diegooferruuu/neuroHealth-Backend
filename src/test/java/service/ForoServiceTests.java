package service;

import com.example.backend.model.Publicacion;
import com.example.backend.model.Usuario;
import com.example.backend.service.UsuarioService;
import com.example.backend.service.ForoService;
import com.example.backend.repository.PublicacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class ForoServiceTests {
    @Mock
    private PublicacionRepository publicacionRepository;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private ForoService foroService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrearPublicacion() {
        String usuarioId = "123";
        String titulo = "Título de prueba";
        String contenido = "Contenido de prueba";
        String tema = "Tema de prueba";

        // Crear usuario simulado
        Usuario usuarioSimulado = new Usuario();
        usuarioSimulado.setId(usuarioId);
        usuarioSimulado.setNombre("Usuario Test");
        usuarioSimulado.setEmail("test@example.com");

        // Publicación simulada
        Publicacion publicacionSimulada = new Publicacion(usuarioSimulado, titulo, contenido, tema);

        when(usuarioService.obtenerUsuarioPorId(usuarioId)).thenReturn(usuarioSimulado);
        when(publicacionRepository.save(any(Publicacion.class))).thenReturn(publicacionSimulada);

        Publicacion resultado = foroService.crearPublicacion(usuarioId, titulo, contenido, tema);

        // Verificar
        assertNotNull(resultado);
        assertEquals(titulo, resultado.getTitulo());
        assertEquals(contenido, resultado.getContenido());
        assertEquals(tema, resultado.getTema());
        assertEquals(usuarioSimulado, resultado.getUsuario());
    }
}
