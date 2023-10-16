package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.modelo.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepo extends JpaRepository<Paciente, Integer> {

    Paciente findByTipoNumeroDocumento(Integer idTipoDocumento, String numeroDocumento);


}
