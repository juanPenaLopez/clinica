package co.edu.uniquindio.clinica.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
public class DetalleMedicoDTO {

    @Positive
    private Integer idMedico;

    @NotEmpty
    @Email
    @Length(max = 80)
    private String correo;

    @NotEmpty
    @Length(max = 10)
    private String contrasena;

    @NotEmpty
    @Length(max = 20)
    private String telefono;

    @NotEmpty
    String urlFoto;

    @NotEmpty
    private List<HorarioDTO> horarios;

    @NotEmpty
    private String numeroIdentificacion;

    @NotEmpty
    @Length(max = 150)
    private String nombreCompleto;

    @NotEmpty
    @NotBlank
    private Integer idEspecialidad;

    @NotEmpty
    @NotEmpty
    private Integer idCiudad;

}