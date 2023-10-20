package co.edu.uniquindio.clinica.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ActualizarDTO {

    @NotEmpty
    private String numeroIdentificacion;

    @NotEmpty
    private String nombreCompleto;

    @NotEmpty
    private String telefono;

    @NotEmpty
    private Integer idPaciente;

    private String urlFoto;

    private String correo;

    private Integer idCiudad;
}
