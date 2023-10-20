package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.modelo.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadRepo extends JpaRepository<Especialidad, Integer> {
}
