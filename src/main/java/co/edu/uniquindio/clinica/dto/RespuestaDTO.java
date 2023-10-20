package co.edu.uniquindio.clinica.dto;

import java.time.LocalDateTime;

public record RespuestaDTO (
        Integer codigoMensaje,
        String mensaje,
        String nombreUsuario,
        LocalDateTime fecha) {
}
