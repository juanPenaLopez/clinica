package co.edu.uniquindio.clinica.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DiaLibre implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer idDiaLibre;

    private LocalDate fechaDiaLibre;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMedico")
    private Medico medico;
}
