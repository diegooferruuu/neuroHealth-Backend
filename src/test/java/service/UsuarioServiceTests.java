package service;

import com.example.backend.model.Usuario;
import com.example.backend.service.UsuarioService;
import com.example.backend.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioServiceTests {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIniciarSesion_UsuarioYContrasenaCorrecta() {
        String email = "prueba@example.com";
        String contrasena = "1234";

        Usuario usuarioSimulado = new Usuario();
        usuarioSimulado.setEmail(email);
        usuarioSimulado.setContrasena(contrasena);

        when(usuarioRepository.findByEmail(email)).thenReturn(usuarioSimulado);

        // Ejecuta
        Usuario resultado = usuarioService.iniciarSesion(email, contrasena);

        // Verifica
        assertNotNull(resultado);
        assertEquals(email, resultado.getEmail());
    }

    @Test
    public void testIniciarSesion_UsuarioNoExisteOContrasenaIncorrecta() {
        String emailInvalido = "noexiste@correo.com";
        when(usuarioRepository.findByEmail(emailInvalido)).thenReturn(null);

        Usuario resultado1 = usuarioService.iniciarSesion(emailInvalido, "123456");
        assertNull(resultado1);

        Usuario usuarioSimulado = new Usuario();
        usuarioSimulado.setEmail("ejemplo@correo.com");
        usuarioSimulado.setContrasena("correcta");

        when(usuarioRepository.findByEmail("ejemplo@correo.com")).thenReturn(usuarioSimulado);

        Usuario resultado2 = usuarioService.iniciarSesion("ejemplo@correo.com", "incorrecta");
        assertNull(resultado2);
    }
}