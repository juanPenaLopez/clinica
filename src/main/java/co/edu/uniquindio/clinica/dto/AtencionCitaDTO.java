package co.edu.uniquindio.clinica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class AtencionCitaDTO {

    @NotNull
    @NotEmpty
    @NotBlank
    private Integer idCita;

    @NotNull
    @NotBlank
    @Length(max = 45)
    private String diagnostico;

    @NotNull
    @NotEmpty
    @Length(max = 245)
    private String tratamiento;

    @NotNull
    @NotEmpty
    @Length(max = 245)
    private String nota;

    @NotNull
    @NotEmpty
    @Length(max = 245)
    private String sintomas;
}
