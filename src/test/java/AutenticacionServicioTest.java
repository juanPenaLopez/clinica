import co.edu.uniquindio.clinica.dto.LoginDTO;
import co.edu.uniquindio.clinica.dto.TokenDTO;
import co.edu.uniquindio.clinica.modelo.Cuenta;
import co.edu.uniquindio.clinica.modelo.Paciente;
import co.edu.uniquindio.clinica.repositorios.CuentaRepo;
import co.edu.uniquindio.clinica.servicios.impl.AutenticacionServicioImpl;
import co.edu.uniquindio.clinica.utils.JWTUtils;
import jakarta.transaction.Transactional;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class AutenticacionServicioTest {

    private AutenticacionServicioImpl autenticacionServicio;
    private BCryptPasswordEncoder passwordEncoder;
    CuentaRepo cuentaRepo;
    JWTUtils jwtUtils;

    @Before("")
    public void setUp() {
        autenticacionServicio = new AutenticacionServicioImpl(cuentaRepo, jwtUtils);
        passwordEncoder = new BCryptPasswordEncoder();
        cuentaRepo = mock(CuentaRepo.class);
        jwtUtils = mock(JWTUtils.class);
    }

    @Test
    public void credencialesValidas() throws Exception {
        // Se preparan los datos de prueba
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setCorreo("correoValido");
        loginDTO.setContrasena("contrasenaValida");

        Cuenta cuenta = new Cuenta();
        cuenta.setContrasena(passwordEncoder.encode("contrasenaValida"));

        // Simular el comportamiento del repositorio
        when(cuentaRepo.findByCorreo(loginDTO.getCorreo())).thenReturn(Optional.of(cuenta));

        // Simular la generación de token
        when(jwtUtils.generarToken(eq(cuenta.getEmail()), anyMap())).thenReturn("tokenGenerado");

        // Realizar la prueba
        TokenDTO token = autenticacionServicio.login(loginDTO);

        // Verificar que se generó un token
        assertEquals("Se esperaba un token", "tokenGenerado", token.getToken());
    }

    @Test
    public void correoInvalido() {
        // Preparar datos de prueba
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setCorreo("correoInvalido");
        loginDTO.setContrasena("contrasenaValida");

        // Simular el comportamiento del repositorio
        when(cuentaRepo.findByCorreo(loginDTO.getCorreo())).thenReturn(Optional.empty());

        // Realizar la prueba y verificar que se lanza una excepción
        Exception exception = assertThrows(Exception.class, () ->
            autenticacionServicio.login(loginDTO));

        // Verificar el mensaje de la excepción
        assertEquals("No existe el correo ingresado", exception.getMessage());
    }

    @Test
    public void contrasenaInvalida() {
        // Preparar datos de prueba
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setCorreo("correoValido");
        loginDTO.setContrasena("contrasenaInvalida");

        Cuenta cuenta = new Paciente(); // Puedes usar cualquier cuenta válida para tu caso
        cuenta.setContrasena(passwordEncoder.encode("contrasenaValida"));

        // Simular el comportamiento del repositorio
        when(cuentaRepo.findByCorreo(loginDTO.getCorreo())).thenReturn(Optional.of(cuenta));

        // Realizar la prueba y verificar que se lanza una excepción
        Exception exception = assertThrows(Exception.class, () ->
            autenticacionServicio.login(loginDTO));

        // Verificar el mensaje de la excepción
        assertEquals("La contraseña ingresada es incorrecta", exception.getMessage());
    }
}
