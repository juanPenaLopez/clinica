package co.edu.uniquindio.clinica.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Alergia implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer idAlergia;

    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "alergia")
    private List<AlergiaPaciente> alergiaPacienteList;
}
