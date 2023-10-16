package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.modelo.HorarioMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRepo extends JpaRepository<HorarioMedico, Integer> {
}
