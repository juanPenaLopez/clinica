package co.edu.uniquindio.clinica.modelo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolicitarCitaDTO {

    @NotNull
    @NotEmpty
    @NotBlank
    private Integer idEspecialidad;

    private Integer idMedico;
}
