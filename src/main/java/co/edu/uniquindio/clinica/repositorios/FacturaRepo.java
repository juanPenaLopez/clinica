package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.modelo.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepo extends JpaRepository<Factura, Integer> {

}
