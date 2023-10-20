package co.edu.uniquindio.clinica.dto;

import co.edu.uniquindio.clinica.modelo.Especialidad;
import co.edu.uniquindio.clinica.modelo.EstadoCita;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public record ItemCitaAdminDTO(

        Integer codigoCita,
        String cedulaPaciente,
        String nombrePaciente,
        String nombreMedico,
        Especialidad especialidad,
        EstadoCita estadoCita,
        LocalDateTime fecha

) {
    public ItemCitaAdminDTO(Integer idCita, String numeroDocumento, String nombre, String nombre1, Especialidad especialidad, EstadoCita estadoCita, LocalDateTime fechaCita) {
        this.codigoCita = idCita;
        this.cedulaPaciente = numeroDocumento;
        this.nombrePaciente = nombre;
        this.nombreMedico = nombre1;
        this.especialidad = especialidad;
        this.estadoCita = estadoCita;
        this.fecha = fechaCita;
    }
}
