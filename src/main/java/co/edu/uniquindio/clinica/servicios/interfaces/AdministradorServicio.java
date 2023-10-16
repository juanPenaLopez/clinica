package co.edu.uniquindio.clinica.servicios.interfaces;

import co.edu.uniquindio.clinica.dto.*;
import co.edu.uniquindio.clinica.modelo.EstadoPQRS;

import java.util.List;

import java.util.List;

public interface AdministradorServicio {

    int crearMedico(RegistrarMedicoDTO medicoDTO) throws Exception;

    int actualizarMedico(DetalleMedicoDTO medicoDTO) throws Exception;

    void eliminarMedico(Integer idMedico) throws Exception;

    List<ItemMedicoDTO> listarMedicos() throws Exception;

    DetalleMedicoDTO obtenerMedico(Integer idMedico) throws Exception;

    List<ItemPQRSDTO> listarPQRS() throws Exception;

    DetallePQRSDTO verDetallePQRS(int codigo) throws Exception;

    int responderPQRS(RegistroRespuestaDTO registroRespuestaDTO) throws Exception;

    void cambiarEstadoPQRS(int codigoPQRS, EstadoPQRSDTO estadoPQRS) throws Exception;

    List<ItemCitaAdminDTO> listarCitas() throws Exception;

}
