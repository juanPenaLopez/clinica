package co.edu.uniquindio.clinica.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cita implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer idCita;

    private LocalDate fechaSolicitud;

    private LocalDate fechaCita;

    private String motivo;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPaciente")
    private Paciente paciente;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEstadoCita")
    private EstadoCita estadoCita;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMedico")
    private Medico medico;
}
