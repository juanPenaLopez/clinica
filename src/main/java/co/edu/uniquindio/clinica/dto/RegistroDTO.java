package co.edu.uniquindio.clinica.dto;

import co.edu.uniquindio.clinica.modelo.TipoSangre;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegistroDTO {

    private Integer idTipoIdentificacion;

    private Integer numeroIdentificacion;

    private String nombreCompleto;

    private String contrasena;

    private String correo;

    private String telefono;

    private Integer idCiudad;

    private LocalDate fechaNacimiento;

    private TipoSangre tipoSangre;

    private Integer idEps;

    private String urlFoto;
}
