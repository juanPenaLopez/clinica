package co.edu.uniquindio.clinica.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public class DetalleMedicoDTO (

    @Positive
    int idMedico,
    @NotEmpty
    @Email
    @Length(max = 80)
    String correo,
    @NotEmpty
    @Length(max = 10)
    String contraseña,
    @NotEmpty
    @Length(max = 20)
    String telefono,

    @NotEmpty
    String urlFoto,
    @NotEmpty
    List<HorarioDTO> horarios)
{
}