package co.edu.uniquindio.clinica.servicios.interfaces;

import co.edu.uniquindio.clinica.modelo.Paciente;

import java.util.List;

public interface PacienteServicio {

    int registrarse(Paciente paciente) throws Exception;

    int editarPerfil(Paciente paciente) throws Exception;

    int eliminarCuenta(Integer idPaciente) throws Exception;

    Paciente verDetallePaciente (Integer idPaciente) throws Exception;

    Paciente pedirCita (Integer idPaciente) throws Exception;

    Paciente consultarCita (Integer idPaciente) throws Exception;

    Paciente eliminarCita (Integer idPaciente) throws Exception;

    List<Paciente> listarTodos();
}
