package co.edu.uniquindio.clinica.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public record TokenDTO (
        @NotBlank
        String token
){
}
