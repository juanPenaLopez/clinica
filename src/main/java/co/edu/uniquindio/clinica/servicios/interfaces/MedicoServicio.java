package co.edu.uniquindio.clinica.servicios.interfaces;

import co.edu.uniquindio.clinica.dto.CitaDTO;
import co.edu.uniquindio.clinica.dto.ResultadoDTO;
import co.edu.uniquindio.clinica.dto.SolicitarDiaLibreInDTO;
import co.edu.uniquindio.clinica.dto.SolicitarReservaQuirofanoDTO;
import co.edu.uniquindio.clinica.modelo.Medico;
import co.edu.uniquindio.clinica.modelo.Paciente;

import java.util.List;

public interface MedicoServicio {

    ResultadoDTO solicitarDiaLibre(SolicitarDiaLibreInDTO solicitudDiaLibre) throws Exception;

    List<CitaDTO> consultarCitasPendientes (Integer idEstadoCita, Integer idMedico) throws Exception;

    List<CitaDTO> consultarHistorialCitasAtendidas (Integer idMedico) throws Exception;

    Medico consultarHistorialPaciente (Integer idPaciente) throws Exception;

    Medico realizarDiagnosticoPaciente (Integer idPaciente) throws Exception;

    ResultadoDTO solicitarReservaQuirofano (SolicitarReservaQuirofanoDTO solicitudReserva) throws Exception;
}
