package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.modelo.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepo extends JpaRepository<Reserva, Integer> {
}
