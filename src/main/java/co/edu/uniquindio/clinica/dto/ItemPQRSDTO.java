package co.edu.uniquindio.clinica.dto;

import co.edu.uniquindio.clinica.modelo.EstadoPQRS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ItemPQRSDTO {

    private Integer idPQRS;

    private EstadoPQRS estadoPQRS;

    private LocalDate fechaGeneracion;

    private String nombrePaciente;

}
