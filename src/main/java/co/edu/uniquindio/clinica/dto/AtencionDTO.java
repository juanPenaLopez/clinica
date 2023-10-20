package co.edu.uniquindio.clinica.dto;

import co.edu.uniquindio.clinica.modelo.Cita;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtencionDTO {

    private Integer idAtencion;

    private Cita cita;

    private String diagnostico;

    private String tratamiento;

    private String nota;

    private String Sintomas;
}
