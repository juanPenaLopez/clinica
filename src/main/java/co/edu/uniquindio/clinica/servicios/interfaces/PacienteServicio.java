package co.edu.uniquindio.clinica.servicios.interfaces;

import co.edu.uniquindio.clinica.dto.ActualizarDTO;
import co.edu.uniquindio.clinica.dto.CitaDTO;
import co.edu.uniquindio.clinica.dto.RegistroDTO;
import co.edu.uniquindio.clinica.dto.ResultadoDTO;
import co.edu.uniquindio.clinica.modelo.Paciente;

import java.util.List;

public interface PacienteServicio {

    ResultadoDTO registrarse(RegistroDTO paciente) throws Exception;

    ResultadoDTO editarPerfil(ActualizarDTO actualizarDTO) throws Exception;

    ResultadoDTO eliminarCuenta(Integer idPaciente) throws Exception;

    Paciente verDetallePaciente (Integer idPaciente) throws Exception;

    Paciente pedirCita (Integer idPaciente) throws Exception;

    Paciente consultarCita (Integer idPaciente) throws Exception;

    Paciente eliminarCita (Integer idPaciente) throws Exception;

    Paciente pagarFactura (Integer idPaciente) throws Exception;

    List<CitaDTO> listarCitas(Integer idPaciente) throws Exception;
}
