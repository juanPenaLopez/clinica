package co.edu.uniquindio.clinica.dto;

import co.edu.uniquindio.clinica.modelo.EstadoCita;
import co.edu.uniquindio.clinica.modelo.Medico;
import co.edu.uniquindio.clinica.modelo.Paciente;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CitaDTO {

    private Integer idCita;

    private LocalDate fechaSolicitud;

    private LocalDate fechaCita;

    private String motivo;

    private Paciente paciente;

    private EstadoCita estadoCita;

    private Medico medico;
}
