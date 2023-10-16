package co.edu.uniquindio.clinica.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDTO {

    private String asunto;

    private String cuerpo;

    private String destinatario;
}
