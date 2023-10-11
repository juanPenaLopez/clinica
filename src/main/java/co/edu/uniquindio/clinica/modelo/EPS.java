package co.edu.uniquindio.clinica.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EPS implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer idEPS;

    private String codigo;

    private String nombre;
}
