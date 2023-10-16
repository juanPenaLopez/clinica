package co.edu.uniquindio.clinica.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AlergiaPaciente implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;

    @ManyToOne
    private Alergia alergia;

    @ManyToOne
    private Paciente paciente;
}
