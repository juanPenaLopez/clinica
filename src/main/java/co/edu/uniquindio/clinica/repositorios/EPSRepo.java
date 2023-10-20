package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.modelo.EPS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EPSRepo extends JpaRepository<EPS, Integer> {
}
