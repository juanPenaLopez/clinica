package co.edu.uniquindio.clinica.dto;

import co.edu.uniquindio.clinica.modelo.TipoSangre;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Getter
@Setter
public class RegistroDTO {

    @NotEmpty
    private Integer idTipoIdentificacion;

    @NotNull
    @NotEmpty
    private String numeroIdentificacion;

    @NotBlank
    @Length(max = 200)
    private String nombreCompleto;

    @NotBlank
    private String contrasena;

    @NotBlank
    @Email
    @Length(max = 80)
    private String correo;

    @NotBlank
    @Length(max = 20)
    private String telefono;

    @NotBlank
    @NotNull
    private Integer idCiudad;

    @NotBlank
    @NotNull
    private LocalDate fechaNacimiento;

    @NotBlank
    private TipoSangre tipoSangre;

    @NotBlank
    @NotNull
    private Integer idEps;

    @NotEmpty
    private String urlFoto;
}
