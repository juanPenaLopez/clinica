package co.edu.uniquindio.clinica.servicios.impl;

import co.edu.uniquindio.clinica.dto.*;
import co.edu.uniquindio.clinica.modelo.*;
import co.edu.uniquindio.clinica.repositorios.*;
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

    private final CiudadRepo ciudadRepo;

    private final EPSRepo epsRepo;

    private final TipoIdentificacionRepo tipoIdentificacionRepo;

    private final HorarioRepo horarioRepo;

    @Override
    public ResultadoDTO registrarse(RegistroDTO registro) throws Exception {

        ResultadoDTO resultadoDTO = new ResultadoDTO();

        if( estaRepetidaCedula(registro.getNumeroIdentificacion()) ){
            resultadoDTO.setEsExitoso(false);
            resultadoDTO.getMensajesError().add("La cédula " +registro.getNumeroIdentificacion()+" ya está en uso");
            return resultadoDTO;
        }

        if( estaRepetidoCorreo(registro.getCorreo()) ){
            resultadoDTO.setEsExitoso(false);
            resultadoDTO.getMensajesError().add("El correo " +registro.getCorreo()+" ya está en uso");
            return resultadoDTO;
        }

        Optional<Ciudad> ciudadBuscada = ciudadRepo.findById(registro.getIdCiudad());
        Optional<EPS> epsBuscada = epsRepo.findById(registro.getIdEps());
        Optional<TipoIdentificacion> tipoIdentificacion = tipoIdentificacionRepo.findById(registro.getIdTipoIdentificacion());

        Paciente paciente = new Paciente();
        paciente.setEstadoPersona(EstadoPersona.ACTIVO);
        paciente.setNombre(registro.getNombreCompleto());
        paciente.setEmail(registro.getCorreo());
        paciente.setTelefono(registro.getTelefono());
        paciente.setFechaNacimiento(registro.getFechaNacimiento());
        paciente.setUrlFoto(registro.getUrlFoto());
        paciente.setTipoSangre(registro.getTipoSangre());
        paciente.setCiudad(ciudadBuscada.get());
        paciente.setEps(epsBuscada.get());
        paciente.setTipoIdentificacion(tipoIdentificacion.get());

        pacienteRepo.save(paciente);
        resultadoDTO.setEsExitoso(true);
        resultadoDTO.getMensajesInfo().add("Se creó correctamente el paciente");

        return resultadoDTO;
    }

    @Override
    public ResultadoDTO editarPerfil(ActualizarDTO actualizarDTO) throws Exception {

        ResultadoDTO resultado = new ResultadoDTO();

        Optional<Paciente> pacienteBuscado = pacienteRepo.findById(actualizarDTO.getIdPaciente());
        Optional<Ciudad> ciudadBuscada = ciudadRepo.findById(actualizarDTO.getIdCiudad());

        if(pacienteBuscado.isEmpty()){
            resultado.setEsExitoso(false);
            resultado.getMensajesError().add("No se encuentra el paciente con id: " + actualizarDTO.getIdPaciente());
            return resultado;
        }

        Paciente paciente = pacienteBuscado.get();

        paciente.setNombre(actualizarDTO.getNombreCompleto());
        paciente.setNumeroDocumento(actualizarDTO.getNumeroIdentificacion());
        paciente.setTelefono(actualizarDTO.getTelefono());
        paciente.setUrlFoto(actualizarDTO.getUrlFoto());
        paciente.setEmail(actualizarDTO.getCorreo());

        if(!ciudadBuscada.isEmpty()){
            Ciudad ciudad = ciudadBuscada.get();
            paciente.setCiudad(ciudad);
        }

        pacienteRepo.save(paciente);
        resultado.setEsExitoso(true);
        resultado.getMensajesInfo().add("Se actualizó correctamente la información");

        return resultado;
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

        Optional<Paciente> pacienteBuscado = pacienteRepo.findById(idPaciente);

        if(pacienteBuscado.isEmpty()){
            throw new Exception("No se encuentra el paciente con id: " + idPaciente);
        }

        return pacienteBuscado.get();
    }

    @Override
    public ResultadoDTO pedirCita(SolicitarCitaDTO solicitarCitaDTO) throws Exception {

        ResultadoDTO resultadoDTO = new ResultadoDTO();

        return resultadoDTO;
    }

    @Override
    public CitaDTO consultarCita(ConsultarCitaDTO consultarCitaDTO) throws Exception {

        Optional<Cita> citaBuscada = citaRepo.findById(consultarCitaDTO.getIdCita());

        if(citaBuscada.isEmpty()){
            throw new Exception("No existe la cita seleccionada");
        }

        Cita cita = citaBuscada.get();
        CitaDTO citaDTO = new CitaDTO();

        citaDTO.setIdCita(cita.getIdCita());
        citaDTO.setEstadoCita(cita.getEstadoCita());
        citaDTO.setPaciente(cita.getPaciente());
        citaDTO.setFechaCita(cita.getFechaCita());
        citaDTO.setMotivo(cita.getMotivo());
        citaDTO.setMedico(cita.getMedico());
        citaDTO.setFechaSolicitud(cita.getFechaSolicitud());

        return citaDTO;
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

    @Override
    public void consultarDisponibilidad(SolicitarCitaDTO solicitarCitaDTO) throws Exception {

    }

    private boolean estaRepetidaCedula(String cedula) {
        return pacienteRepo.findByCedula(cedula) != null;
    }

    private boolean estaRepetidoCorreo(String correo) {
        return pacienteRepo.findByCorreo(correo) != null;
    }
}
