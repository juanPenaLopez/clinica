package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.modelo.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepo extends JpaRepository<Medico, Integer> {

    Medico findByTipoNumeroDocumento(Integer idTipoDocumento, String numeroDocumento);
}
