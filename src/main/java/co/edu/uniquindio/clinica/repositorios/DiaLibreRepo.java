package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.modelo.DiaLibre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DiaLibreRepo extends JpaRepository<DiaLibre, Integer> {

    DiaLibre findByFechaMedico(LocalDate fecha, Integer idmedico);
}
