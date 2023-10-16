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
public class Reserva implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer idReserva;

    private LocalDate fechaSolicitud;

    private LocalDate fechaReserva;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMedico")
    private Medico medico;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEstadoReserva")
    private EstadoReserva estadoReserva;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idQuirofano")
    private Quirofano quirofano;

    private LocalDate fechaAprobacion;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAdministrador")
    private Administrador administrador;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPaciente")
    private Paciente paciente;

    private String mensaje;
}
