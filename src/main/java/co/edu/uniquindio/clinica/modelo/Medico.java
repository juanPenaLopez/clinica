package co.edu.uniquindio.clinica.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medico extends Cuenta implements Serializable{

    private String nombre;

    private String numeroDocumento;

    private String telefono;

    private String urlFoto;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCiudad")
    private Ciudad ciudad;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEspecialidad")
    private Especialidad especialidad;

    private EstadoPersona estadoPersona;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoIdentificacion")
    private TipoIdentificacion tipoIdentificacion;
}
