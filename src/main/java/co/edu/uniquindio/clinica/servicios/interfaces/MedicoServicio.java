package co.edu.uniquindio.clinica.servicios.interfaces;

import co.edu.uniquindio.clinica.dto.*;
import co.edu.uniquindio.clinica.modelo.Medico;
import co.edu.uniquindio.clinica.modelo.Paciente;

import java.util.List;

public interface MedicoServicio {

    ResultadoDTO solicitarDiaLibre(SolicitarDiaLibreInDTO solicitudDiaLibre) throws Exception;

    List<CitaDTO> consultarCitasPendientes (Integer idEstadoCita, Integer idMedico) throws Exception;

    List<CitaDTO> consultarHistorialCitasAtendidas (Integer idMedico) throws Exception;

    List<AtencionDTO> consultarHistorialPaciente (Integer idPaciente) throws Exception;

    ResultadoDTO realizarDiagnosticoPaciente (AtencionCitaDTO atencionCitaDTO) throws Exception;

    ResultadoDTO solicitarReservaQuirofano (SolicitarReservaQuirofanoDTO solicitudReserva) throws Exception;
}
