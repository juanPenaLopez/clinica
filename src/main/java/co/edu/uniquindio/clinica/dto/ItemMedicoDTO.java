package co.edu.uniquindio.clinica.dto;

import co.edu.uniquindio.clinica.modelo.Especialidad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ItemMedicoDTO {

    private Integer idCuenta;

    private String numeroDocumento;

    private String nombre;

    private String urlFoto;

    private Especialidad especialidad;
}