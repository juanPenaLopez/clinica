package co.edu.uniquindio.clinica.dto;

import co.edu.uniquindio.clinica.modelo.Especialidad;
import co.edu.uniquindio.clinica.modelo.EstadoPQRS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class DetallePQRSDTO {

    private Integer idPQRS;

    private EstadoPQRS estadoPQRS;

    private String nombrePaciente;

    private String nombreMedico;

    private Especialidad especialidad;

    private LocalDate fechaGeneracion;

    private List<RespuestaDTO> respuestaDTOS;
}
