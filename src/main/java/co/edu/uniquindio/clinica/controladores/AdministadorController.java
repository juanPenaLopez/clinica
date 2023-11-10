package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.dto.*;
import co.edu.uniquindio.clinica.modelo.EstadoPQRS;
import co.edu.uniquindio.clinica.servicios.interfaces.AdministradorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/administradores")
public class AdministadorController {

    private final AdministradorServicio administradorServicio;

    @PostMapping("/crear-medico")
    private int crearMedico(RegistroMedicoDTO medicoDTO) throws Exception {
        return 0;
    }

    @PutMapping("/actualizar-medico")
    private int actualizarMedico(DetalleMedicoDTO medicoDTO) throws Exception {
        return 0;
    }

    @DeleteMapping("/eliminar-medico")
    private void eliminarMedico(Integer idMedico) throws Exception{

    }

    @GetMapping("/listar-medicos")
    private List<ItemMedicoDTO> listarMedicos() throws Exception{
        return null;
    }

    @GetMapping("/detalle-medico")
    private DetalleMedicoDTO obtenerMedico(Integer idMedico) throws Exception{
        return null;
    }

    @GetMapping("/pqrs")
    private List<ItemPQRSDTO> listarPQRS() throws Exception{
        return null;
    }

    @GetMapping("/detalle-pqrs")
    private DetallePQRSDTO verDetallePQRS(int codigo) throws Exception{
        return null;
    }

    @PutMapping("/responder-pqrs")
    private int responderPQRS(RegistroRespuestaDTO registroRespuestaDTO) throws Exception{
        return 0;
    }

    @PutMapping("/cambiar-estado-pqrs")
    private void cambiarEstadoPQRS(int codigoPQRS, EstadoPQRS estadoPQRS) throws Exception{

    }

    @GetMapping("/citas")
    private List<ItemCitaAdminDTO> listarCitas() throws Exception{
        return null;
    }
}
