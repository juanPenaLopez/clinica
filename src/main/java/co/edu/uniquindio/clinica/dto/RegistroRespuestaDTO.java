package co.edu.uniquindio.clinica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public record RegistroRespuestaDTO(
        @Positive
        int codigoCuenta,
        @Positive
        int codigoPQRS,
        @Positive
        int codigoMensaje,
        @NotBlank
        String mensaje
) {
}
