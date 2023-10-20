package co.edu.uniquindio.clinica.servicios.interfaces;

import co.edu.uniquindio.clinica.dto.*;
import co.edu.uniquindio.clinica.modelo.Paciente;
import co.edu.uniquindio.clinica.modelo.SolicitarCitaDTO;

import java.util.List;

public interface PacienteServicio {

    ResultadoDTO registrarse(RegistroDTO paciente) throws Exception;

    ResultadoDTO editarPerfil(ActualizarDTO actualizarDTO) throws Exception;

    ResultadoDTO eliminarCuenta(Integer idPaciente) throws Exception;

    Paciente verDetallePaciente (Integer idPaciente) throws Exception;

    ResultadoDTO pedirCita (SolicitarCitaDTO solicitarCitaDTO) throws Exception;

    CitaDTO consultarCita (ConsultarCitaDTO consultarCitaDTO) throws Exception;

    Paciente pagarFactura (Integer idPaciente) throws Exception;

    List<CitaDTO> listarCitas(Integer idPaciente) throws Exception;

    void consultarDisponibilidad(SolicitarCitaDTO solicitarCitaDTO) throws Exception;
}
