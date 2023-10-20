package co.edu.uniquindio.clinica.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    private String contraseña;

    @NotEmpty
    @Length(max = 20)
    private String telefono;

    @NotEmpty
    String urlFoto;
    @NotEmpty
    private List<HorarioDTO> horarios;

}