package co.edu.uniquindio.clinica.dto;

import co.edu.uniquindio.clinica.modelo.Paciente;
import co.edu.uniquindio.clinica.modelo.Quirofano;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Getter
@Setter
public class SolicitarReservaQuirofanoDTO {

    private LocalDate fechaSolicitud;

    @NotNull
    private LocalDate fechaSolicitudReserva;

    @NotNull
    private Integer idQuirofano;

    @NotNull
    private Paciente paciente;

    @NotNull
    private Integer idMedico;
}
