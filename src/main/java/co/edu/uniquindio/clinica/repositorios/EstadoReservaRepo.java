package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.modelo.Cita;
import co.edu.uniquindio.clinica.modelo.EstadoReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoReservaRepo extends JpaRepository<Cita, Integer> {

    EstadoReserva findByEstado(String estado);
}
