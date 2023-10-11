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
public class Paciente implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer idPaciente;

    private String email;

    private String contrasena;

    private String numeroDocumento;

    private String nombreCompleto;

    private String telefono;

    private String urlFoto;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCiudad")
    private Ciudad ciudad;

    private LocalDate fechaNacimiento;

    private TipoSangre tipoSangre;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEPS")
    private EPS eps;

    private EstadoPersona estadoPersona;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoIdentificacion")
    private TipoIdentificacion tipoIdentificacion;
}
