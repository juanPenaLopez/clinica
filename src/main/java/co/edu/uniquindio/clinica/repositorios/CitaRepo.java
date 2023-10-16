package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.modelo.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepo extends JpaRepository<Cita, Integer> {

}
