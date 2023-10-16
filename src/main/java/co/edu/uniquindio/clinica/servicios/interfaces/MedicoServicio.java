package co.edu.uniquindio.clinica.servicios.interfaces;

import co.edu.uniquindio.clinica.modelo.Medico;
import co.edu.uniquindio.clinica.modelo.Paciente;

public interface MedicoServicio {

    int registrarMedico(Medico medico) throws Exception;

    int editarPerfil(Medico medico) throws Exception;

    int eliminarCuenta(Integer idMedico) throws Exception;

    int solicitarDiaLibre(Integer idMedico) throws Exception;

    Medico verDetalleMedico (Integer idMedico) throws Exception;

    Medico consultarCitasPendientes (Integer idMedico) throws Exception;

    Medico consultarHistorialCitasAtendidas (Integer idMedico) throws Exception;

    Medico consultarHistorialPaciente (Integer idPaciente) throws Exception;

    Medico realizarDiagnosticoPaciente (Integer idPaciente) throws Exception;

    Medico solicitarReservaQuirofano (Integer idMedico) throws Exception;

    Medico aceptarRechazarReserva (Integer idMedico) throws Exception;
}
