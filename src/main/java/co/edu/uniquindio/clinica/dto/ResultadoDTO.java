package co.edu.uniquindio.clinica.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResultadoDTO implements Serializable {

    private Boolean esExitoso;

    private List<String> mensajesError = new ArrayList<>();

    private List<String> mensajesInfo = new ArrayList<>();
}
