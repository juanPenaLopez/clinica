package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.modelo.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, Integer> {
}
