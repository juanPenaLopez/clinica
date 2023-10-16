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
public class PQRS implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer idPQRS;

    private LocalDate fechaGeneracion;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCita")
    private Cita cita;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEstadoPQRS")
    private EstadoPQRS estadoPQRS;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoSolicitud")
    private TipoSolicitud tipoSolicitud;
}
