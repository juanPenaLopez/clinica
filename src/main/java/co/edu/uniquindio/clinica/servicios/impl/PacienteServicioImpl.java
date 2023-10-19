package co.edu.uniquindio.clinica.servicios.impl;

import co.edu.uniquindio.clinica.dto.ActualizarDTO;
import co.edu.uniquindio.clinica.dto.CitaDTO;
import co.edu.uniquindio.clinica.dto.RegistroDTO;
import co.edu.uniquindio.clinica.dto.ResultadoDTO;
import co.edu.uniquindio.clinica.modelo.EstadoPersona;
import co.edu.uniquindio.clinica.modelo.Paciente;
import co.edu.uniquindio.clinica.repositorios.CitaRepo;
import co.edu.uniquindio.clinica.repositorios.PacienteRepo;
import co.edu.uniquindio.clinica.servicios.interfaces.PacienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PacienteServicioImpl implements PacienteServicio {

    private final PacienteRepo pacienteRepo;

    private final CitaRepo citaRepo;

    @Override
    public ResultadoDTO registrarse(RegistroDTO paciente) throws Exception {
        return null;
    }

    @Override
    public ResultadoDTO editarPerfil(ActualizarDTO actualizarDTO) throws Exception {
        return null;
    }

    @Override
    public ResultadoDTO eliminarCuenta(Integer idPaciente) throws Exception {

        Optional<Paciente> opcional = pacienteRepo.findById(idPaciente);
        ResultadoDTO resultadoDTO = new ResultadoDTO();

        if(opcional.isEmpty()){
            resultadoDTO.setEsExitoso(false);
            resultadoDTO.getMensajesError().add("No existe el paciente con el id: " + idPaciente);
            return resultadoDTO;
        }

        Paciente pacienteBuscado = opcional.get();
        pacienteBuscado.setEstadoPersona(EstadoPersona.INACTIVO);
        pacienteRepo.save(pacienteBuscado);
        resultadoDTO.setEsExitoso(true);

        return resultadoDTO;
    }

    @Override
    public Paciente verDetallePaciente(Integer idPaciente) throws Exception {
        return null;
    }

    @Override
    public Paciente pedirCita(Integer idPaciente) throws Exception {
        return null;
    }

    @Override
    public Paciente consultarCita(Integer idPaciente) throws Exception {
        return null;
    }

    @Override
    public Paciente eliminarCita(Integer idPaciente) throws Exception {
        return null;
    }

    @Override
    public Paciente pagarFactura(Integer idPaciente) throws Exception {
        return null;
    }

    @Override
    public List<CitaDTO> listarCitas(Integer idPaciente) throws Exception {

        List<CitaDTO> listaCitas = citaRepo.findByPaciente(idPaciente);

        if(listaCitas.isEmpty()){
            throw new Exception("No se encuentran citas para el paciente con id: " + idPaciente);
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
}
