package co.edu.uniquindio.clinica.modelo;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Factura implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer idFactura;

    private LocalDate fechaFacturacion;

    private BigDecimal monto;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCita")
    private Cita cita;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMedioPago")
    private MedioPago medioPago;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEstadoPago")
    private EstadoPago estadoPago;
}
