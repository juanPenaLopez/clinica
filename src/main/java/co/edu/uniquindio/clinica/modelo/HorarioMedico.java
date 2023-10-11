package co.edu.uniquindio.clinica.modelo;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalTime;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HorarioMedico implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer idHorarioMedico;

    private DiasSemana diasSemana;

    private LocalTime horaInicio;

    private LocalTime horaFinal;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMedico")
    private Medico medico;
}
