package co.edu.uniquindio.clinica.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente extends Cuenta implements Serializable {

    private String nombre;

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

    @OneToMany(mappedBy = "paciente")
    private List<AlergiaPaciente> alergiaPacienteList;
}
