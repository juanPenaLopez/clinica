package co.edu.uniquindio.clinica.servicios.impl;

import co.edu.uniquindio.clinica.dto.*;
import co.edu.uniquindio.clinica.modelo.*;
import co.edu.uniquindio.clinica.repositorios.*;
import co.edu.uniquindio.clinica.servicios.interfaces.MedicoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicoServicioImpl implements MedicoServicio {

    private final ReservaRepo reservaRepo;

    private final MedicoRepo medicoRepo;

    private final DiaLibreRepo diaLibreRepo;

    private final CitaRepo citaRepo;

    private final EstadoCitaRepo estadoCitaRepo;

    private final EstadoReservaRepo estadoReservaRepo;

    private final QuirofanoRepo quirofanoRepo;

    private final AtencionRepo atencionRepo;

    @Override
    public ResultadoDTO solicitarDiaLibre(SolicitarDiaLibreInDTO solicitudDiaLibre) throws Exception {

        Optional<Medico> optional = medicoRepo.findById(solicitudDiaLibre.getIdMedico());
        ResultadoDTO resultado = new ResultadoDTO();

        if(optional.isEmpty()){
            resultado.setEsExitoso(false);
            resultado.getMensajesError().add("No existe el medico con id: " + solicitudDiaLibre.getIdMedico());
            return resultado;
        }

        DiaLibre diaAgendado = diaLibreRepo.
                findByFechaMedico(solicitudDiaLibre.getFechaSolicitudDiaLibre(), solicitudDiaLibre.getIdMedico());

        if(diaAgendado != null){
            resultado.setEsExitoso(false);
            resultado.getMensajesError().add("El medico ya cuenta con un dia libre para la fecha solicitada");
            return resultado;
        }

        Medico medicoBuscado = optional.get();

        DiaLibre diaLibreRegistrado = new DiaLibre();
        diaLibreRegistrado.setMedico(medicoBuscado);
        diaLibreRegistrado.setFechaDiaLibre(solicitudDiaLibre.getFechaSolicitudDiaLibre());

        diaLibreRepo.save(diaLibreRegistrado);
        resultado.setEsExitoso(true);

        return resultado;
    }

    @Override
    public List<CitaDTO> consultarCitasPendientes(Integer idEstadoCita, Integer idMedico) throws Exception {

        EstadoCitaDTO estadoCitaDTO = estadoCitaRepo.findByCodigo("PENDIENTE");
        List<CitaDTO> listaCitas = citaRepo.findByEstadoCitaMedico(estadoCitaDTO.getIdEstadoCita(), idMedico);

        if(listaCitas.isEmpty()){
            throw new Exception("No tiene citas pendientes");
        }

        return listaCitas;
    }

    @Override
    public List<CitaDTO> consultarHistorialCitasAtendidas(Integer idMedico) throws Exception {

        List<CitaDTO> listaCitas = citaRepo.findByMedico(idMedico);

        if(listaCitas.isEmpty()){
            throw new Exception("No se encuentran citas para el médico: " + idMedico);
        }

        return listaCitas.stream()
                .map(cita -> {
                    CitaDTO citaDTO = new CitaDTO();
                    citaDTO.setEstadoCita(cita.getEstadoCita());
                    citaDTO.setMotivo(cita.getMotivo());
                    citaDTO.setFechaCita(cita.getFechaCita());
                    citaDTO.setFechaSolicitud(cita.getFechaSolicitud());
                    citaDTO.setMedico(cita.getMedico());
                    citaDTO.setPaciente(cita.getPaciente());
                    return citaDTO;
                }).toList();
    }

    @Override
    public List<AtencionDTO> consultarHistorialPaciente(Integer idPaciente) throws Exception {

        Optional<List<Atencion>> listaAtencionesPaciente = atencionRepo.findByAllPaciente(idPaciente);

        if(listaAtencionesPaciente.isEmpty()){
            throw new Exception("No existen atenciones anteriores para el paciente");
        }

        return listaAtencionesPaciente.get().stream().map(atencion ->{
            AtencionDTO atencionDTO = new AtencionDTO();
            atencionDTO.setIdAtencion(atencion.getIdAtencion());
            atencionDTO.setTratamiento(atencion.getTratamiento());
            atencionDTO.setNota(atencion.getNota());
            atencionDTO.setSintomas(atencion.getSintomas());
            atencionDTO.setCita(atencion.getCita());
            atencionDTO.setDiagnostico(atencionDTO.getDiagnostico());

            return atencionDTO;
        }).toList();
    }

    @Override
    public ResultadoDTO realizarDiagnosticoPaciente(AtencionCitaDTO atencionCitaDTO) throws Exception {

        Optional<Cita> citaBuscada = citaRepo.findById(atencionCitaDTO.getIdCita());
        ResultadoDTO resultadoDTO = new ResultadoDTO();

        if(citaBuscada.isEmpty()){
            resultadoDTO.setEsExitoso(false);
            resultadoDTO.getMensajesError().add("No existe cita con el id: " + atencionCitaDTO.getIdCita());
        }

        Atencion atencionCita = new Atencion();
        atencionCita.setCita(citaBuscada.get());
        atencionCita.setDiagnostico(atencionCitaDTO.getDiagnostico());
        atencionCita.setNota(atencionCitaDTO.getNota());
        atencionCita.setSintomas(atencionCitaDTO.getSintomas());
        atencionCita.setTratamiento(atencionCitaDTO.getTratamiento());

        atencionRepo.save(atencionCita);
        resultadoDTO.setEsExitoso(true);
        resultadoDTO.getMensajesInfo().add("Se guardó correctamente la atención de la cita");

        return resultadoDTO;
    }

    @Override
    public ResultadoDTO solicitarReservaQuirofano(SolicitarReservaQuirofanoDTO solicitudReserva) throws Exception {

        ResultadoDTO resultadoDTO = new ResultadoDTO();

        Optional<Medico> medicoSolicitante = medicoRepo.findById(solicitudReserva.getIdMedico());
        EstadoReserva estadoReserva = estadoReservaRepo.findByEstado("GENERADA");
        Optional<Quirofano> quirofano = quirofanoRepo.findById(solicitudReserva.getIdQuirofano());

        if(medicoSolicitante.isEmpty()){
            resultadoDTO.setEsExitoso(false);
            resultadoDTO.getMensajesError().add("No se encuentra el medico");
            return resultadoDTO;
        }

        if(quirofano.isEmpty()){
            resultadoDTO.setEsExitoso(false);
            resultadoDTO.getMensajesError().add("No existe el quirofano con id: " +
                    solicitudReserva.getIdQuirofano());
            return resultadoDTO;
        }

        Reserva reserva = new Reserva();
        reserva.setMedico(medicoSolicitante.get());
        reserva.setEstadoReserva(estadoReserva);
        reserva.setQuirofano(quirofano.get());
        reserva.setFechaReserva(solicitudReserva.getFechaSolicitudReserva());
        reserva.setFechaSolicitud(solicitudReserva.getFechaSolicitud());
        reserva.setPaciente(solicitudReserva.getPaciente());

        reservaRepo.save(reserva);
        resultadoDTO.setEsExitoso(true);

        return resultadoDTO;
    }
}
