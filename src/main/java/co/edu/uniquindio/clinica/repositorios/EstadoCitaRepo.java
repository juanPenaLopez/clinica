package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.dto.EstadoCitaDTO;
import co.edu.uniquindio.clinica.modelo.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoCitaRepo extends JpaRepository<EstadoCita, Integer> {

    EstadoCitaDTO findByCodigo(String codigo);
}
