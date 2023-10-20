package co.edu.uniquindio.clinica.servicios.impl;

import co.edu.uniquindio.clinica.dto.*;
import co.edu.uniquindio.clinica.modelo.*;
import co.edu.uniquindio.clinica.repositorios.*;
import co.edu.uniquindio.clinica.servicios.interfaces.AdministradorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministradorServicioImpl implements AdministradorServicio {

    private final MedicoRepo medicoRepo;
    private final PQRSRepo pqrsRepo;
    private final CuentaRepo cuentaRepo;
    private final MensajeRepo mensajeRepo;
    private final CitaRepo citaRepo;
    private final HorarioRepo horarioRepo;
    private final TipoIdentificacionRepo tipoIdentificacionRepo;
    private final EspecialidadRepo especialidadRepo;
    private final CiudadRepo ciudadRepo;

    @Override
    public int crearMedico(RegistroMedicoDTO medicoDTO) throws Exception {

        if( estaRepetidoCorreo(medicoDTO.correo()) ){
            throw new Exception("El correo "+medicoDTO.cedula()+" ya está en uso");
        }

        Optional<TipoIdentificacion> tipoIdentificacion =
                tipoIdentificacionRepo.findById(medicoDTO.idTipoIdentificacion());

        Medico medico = new Medico();
        medico.setNumeroDocumento(medicoDTO.cedula() );
        medico.setTelefono(medicoDTO.telefono());
        medico.setNombre(medicoDTO.nombre() );
        medico.setEspecialidad( medicoDTO.especialidad() );
        medico.setCiudad(medicoDTO.ciudad());
        medico.setEmail(medicoDTO.correo() );
        medico.setContrasena(medicoDTO.password());
        medico.setUrlFoto(medicoDTO.urlFoto());
        medico.setEstadoPersona(EstadoPersona.ACTIVO);

        Medico medicoNuevo = medicoRepo.save(medico);

        asignarHorariosMedico( medicoNuevo, medicoDTO.horarios() );

        return medicoNuevo.getIdCuenta();
    }

    private void asignarHorariosMedico(Medico medicoNuevo, List<HorarioDTO> horarios) {

        for( HorarioDTO h : horarios ){

            HorarioMedico hm = new HorarioMedico();
            hm.setDiasSemana( h.getDia() );
            hm.setHoraInicio( h.getHoraInicio() );
            hm.setHoraFinal( h.getHoraFin() );
            hm.setMedico( medicoNuevo );

            horarioRepo.save(hm);
        }
    }


    private boolean estaRepetidoCorreo(String correo) {

        return medicoRepo.findByCorreo(correo) != null;
    }

    @Override
    public int actualizarMedico(DetalleMedicoDTO medicoDTO) throws Exception {

        Optional<Medico> opcional =medicoRepo.findById(medicoDTO.getIdMedico());

        if( opcional.isEmpty() ){
            throw new Exception("No existe un médico con el código "+medicoDTO.getIdMedico());
        }

        Optional<Especialidad> especialidad = especialidadRepo.findById(medicoDTO.getIdEspecialidad());
        Optional<Ciudad> ciudad = ciudadRepo.findById(medicoDTO.getIdCiudad());

        Medico buscado = opcional.get();

        buscado.setNumeroDocumento(medicoDTO.getNumeroIdentificacion() );
        buscado.setTelefono(medicoDTO.getTelefono());
        buscado.setNombre(medicoDTO.getNombreCompleto() );
        buscado.setEspecialidad( especialidad.get());
        buscado.setCiudad(ciudad.get());
        buscado.setEmail(medicoDTO.getCorreo());
        buscado.setUrlFoto(medicoDTO.getUrlFoto());

        medicoRepo.save( buscado );

        return buscado.getIdCuenta();
    }

    @Override
    public void eliminarMedico(Integer codigo) throws Exception {

        Optional<Medico> opcional =medicoRepo.findById(codigo);

        if( opcional.isEmpty() ){
            throw new Exception("No existe un médico con el código "+codigo);
        }

        Medico buscado = opcional.get();
        buscado.setEstadoPersona(EstadoPersona.INACTIVO);
        medicoRepo.save( buscado );

    }

    @Override
    public List<ItemMedicoDTO> listarMedicos() throws Exception {

        List<Medico> medicos = medicoRepo.findAll();

        if(medicos.isEmpty()){
            throw new Exception("No hay médicos registrados");
        }

        List<ItemMedicoDTO> respuesta = new ArrayList<>();

        for(Medico m: medicos){
            respuesta.add( new ItemMedicoDTO(
                    m.getIdCuenta(),
                    m.getNumeroDocumento(),
                    m.getNombre(),
                    m.getUrlFoto(),
                    m.getEspecialidad()) );
        }

        return respuesta;
    }

    @Override
    public DetalleMedicoDTO obtenerMedico(Integer codigo) throws Exception {

        Optional<Medico> opcional =medicoRepo.findById(codigo);

        if( opcional.isEmpty() ){
            throw new Exception("No existe un médico con el código "+codigo);
        }

        Medico buscado = opcional.get();

        List<HorarioMedico> horarios = horarioRepo.findAllByMedicoCodigo(codigo);
        List<HorarioDTO> horariosDTO = new ArrayList<>();

        for( HorarioMedico h : horarios ){
            horariosDTO.add( new HorarioDTO(
                    h.getDiasSemana(),
                    h.getHoraInicio(),
                    h.getHoraFinal()
            ) );
        }

        DetalleMedicoDTO detalleMedicoDTO = getDetalleMedicoDTO(buscado);

        return detalleMedicoDTO;

    }

    private static DetalleMedicoDTO getDetalleMedicoDTO(Medico buscado) {
        DetalleMedicoDTO detalleMedicoDTO = new DetalleMedicoDTO();
        detalleMedicoDTO.setIdMedico(buscado.getIdCuenta());
        detalleMedicoDTO.setTelefono(buscado.getTelefono());
        detalleMedicoDTO.setNombreCompleto(buscado.getNombre());
        detalleMedicoDTO.setIdCiudad(buscado.getCiudad().idCiudad);
        detalleMedicoDTO.setIdEspecialidad(buscado.getEspecialidad().getIdEspecialidad());
        detalleMedicoDTO.setCorreo(buscado.getEmail());
        detalleMedicoDTO.setUrlFoto(buscado.getUrlFoto());
        return detalleMedicoDTO;
    }

    @Override
    public List<ItemPQRSDTO> listarPQRS() throws Exception {

        List<PQRS> listaPqrs = pqrsRepo.findAll();
        List<ItemPQRSDTO> respuesta = new ArrayList<>();

        for( PQRS p: listaPqrs ){

            respuesta.add( new ItemPQRSDTO(
                    p.getIdPQRS(),
                    p.getEstadoPQRS(),
                    p.getFechaGeneracion(),
                    p.getCita().getPaciente().getNombre()
            ) );
        }
        return respuesta;
    }

    @Override
    public DetallePQRSDTO verDetallePQRS(int codigo) throws Exception {

        Optional<PQRS> opcional = pqrsRepo.findById(codigo);

        if(opcional.isEmpty()){
            throw new Exception("No existe un PQRS con el código "+codigo);
        }

        PQRS buscado = opcional.get();
        List<Mensaje> mensajes = mensajeRepo.findAllByPqrsCodigo(codigo);

        return new DetallePQRSDTO(
                buscado.getIdPQRS(),
                buscado.getEstadoPQRS(),
                buscado.getCita().getPaciente().getNombre(),
                buscado.getCita().getMedico().getNombre(),
                buscado.getCita().getMedico().getEspecialidad(),
                buscado.getFechaGeneracion(),
                convertirRespuestasDTO(mensajes)
        );
    }

    private List<RespuestaDTO> convertirRespuestasDTO(List<Mensaje> mensajes) {
        return mensajes.stream().map(m -> new RespuestaDTO(
                m.getIdMensaje(),
                m.getContenido(),
                m.getAdministrador().getEmail(),
                null
        )).toList();
    }

    @Override
    public int responderPQRS(RegistroRespuestaDTO registroRespuestaDTO) throws Exception {

        Optional<PQRS> opcionalPQRS = pqrsRepo.findById(registroRespuestaDTO.getCodigoPQRS());

        if(opcionalPQRS.isEmpty()){
            throw new Exception("No existe un PQRS con el código "+registroRespuestaDTO.codigoPQRS());
        }

        Optional<Cuenta> opcionalCuenta = cuentaRepo.findById(registroRespuestaDTO.codigoCuenta());

        if(opcionalCuenta.isEmpty()){
            throw new Exception("No existe una cuenta con el código "+registroRespuestaDTO.codigoCuenta());
        }

        Mensaje mensajeNuevo = new Mensaje();
        mensajeNuevo.setPqrs(opcionalPQRS.get());
        mensajeNuevo.setContenido(registroRespuestaDTO.mensaje() );

        Mensaje respuesta = mensajeRepo.save(mensajeNuevo);

        return respuesta.getIdMensaje();
    }

    @Override
    public void cambiarEstadoPQRS(int codigoPQRS, EstadoPQRS estadoPQRS) throws Exception {

        Optional<PQRS> opcional = pqrsRepo.findById(codigoPQRS);

        if( opcional.isEmpty() ){
            throw new Exception("No existe un PQRS con el código "+codigoPQRS);
        }

        PQRS pqrs = opcional.get();
        pqrs.setEstadoPQRS( estadoPQRS );

        pqrsRepo.save( pqrs );
    }

    @Override
    public List<ItemCitaAdminDTO> listarCitas() throws Exception {

        List<Cita> citas = citaRepo.findAll();
        List<ItemCitaAdminDTO> respuesta = new ArrayList<>();

        if(citas.isEmpty()){
            throw new Exception("No existen citas creadas");
        }

        for( Cita c : citas ){
            respuesta.add( new ItemCitaAdminDTO(
                    c.getIdCita(),
                    c.getPaciente().getNumeroDocumento(),
                    c.getPaciente().getNombre(),
                    c.getMedico().getNombre(),
                    c.getMedico().getEspecialidad(),
                    c.getEstadoCita(),
                    c.getFechaCita()
            ) );
        }

        return respuesta;
    }
}
