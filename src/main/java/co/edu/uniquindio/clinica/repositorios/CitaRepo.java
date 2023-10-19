package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.dto.CitaDTO;
import co.edu.uniquindio.clinica.modelo.Cita;
import co.edu.uniquindio.clinica.modelo.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepo extends JpaRepository<Cita, Integer> {

    List<CitaDTO> findByEstadoCitaMedico(Integer idEstadoCita, Integer idMedico);

    List<CitaDTO> findByMedico(Integer idMedico);

    List<CitaDTO> findByPaciente(Integer idPaciente);

}
