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
public class Mensaje implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer idMensaje;

    private String contenido;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPQRS")
    private PQRS pqrs;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMensaje")
    private Mensaje mensajePadre;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAdministrador")
    private Administrador administrador;
}
