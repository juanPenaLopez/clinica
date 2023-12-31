package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.modelo.Quirofano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuirofanoRepo extends JpaRepository<Quirofano, Integer> {
}
