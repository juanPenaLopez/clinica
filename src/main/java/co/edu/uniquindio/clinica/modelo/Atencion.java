package co.edu.uniquindio.clinica.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Atencion implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer idAtencion;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCita")
    private Cita cita;

    private String diagnostico;

    private String tratamiento;

    private String nota;

    private String Sintomas;
}
