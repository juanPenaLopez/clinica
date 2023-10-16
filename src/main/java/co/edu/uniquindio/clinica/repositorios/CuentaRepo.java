package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.modelo.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepo extends JpaRepository<Cuenta, Integer> {

    Cuenta findByCorreo(String correo);
}
