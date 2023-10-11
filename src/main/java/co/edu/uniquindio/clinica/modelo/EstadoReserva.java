package co.edu.uniquindio.clinica.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EstadoReserva implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer idEstadoReserva;

    private String nombreEstado;
}
