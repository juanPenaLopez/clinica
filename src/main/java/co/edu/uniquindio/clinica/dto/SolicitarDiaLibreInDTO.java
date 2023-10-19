package co.edu.uniquindio.clinica.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SolicitarDiaLibreInDTO {

    @NotEmpty
    private Integer idMedico;

    @NotEmpty
    private LocalDate fechaSolicitudDiaLibre;
}
