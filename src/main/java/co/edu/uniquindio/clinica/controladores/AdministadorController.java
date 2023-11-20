package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.dto.*;
import co.edu.uniquindio.clinica.modelo.EstadoPQRS;
import co.edu.uniquindio.clinica.servicios.interfaces.AdministradorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/administradores")
public class AdministadorController {

    private final AdministradorServicio administradorServicio;

    @PostMapping("/crear-medico")
    private ResponseEntity<MensajeDTO<String>> crearMedico(RegistroMedicoDTO medicoDTO) throws Exception {
        administradorServicio.crearMedico(medicoDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "El médico fue creado correctamente"));
    }

    @PutMapping("/actualizar-medico")
    private ResponseEntity<MensajeDTO<String>> actualizarMedico(DetalleMedicoDTO medicoDTO) throws Exception {
        administradorServicio.actualizarMedico(medicoDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se actualizó el médico correctamente"));
    }

    @DeleteMapping("/eliminar-medico/")
    private ResponseEntity<MensajeDTO<String>> eliminarMedico(Integer idMedico) throws Exception{
        administradorServicio.eliminarMedico(idMedico);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "El medico fue eliminado correctamente"));
    }

    @GetMapping("/listar-medicos")
    private ResponseEntity<MensajeDTO<List<ItemMedicoDTO>>> listarMedicos() throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, administradorServicio.listarMedicos()));
    }

    @GetMapping("/detalle-medico/")
    private ResponseEntity<MensajeDTO<DetalleMedicoDTO>> obtenerMedico(Integer idMedico) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, administradorServicio.obtenerMedico(idMedico))) ;
    }

    @GetMapping("/pqrs")
    private ResponseEntity<MensajeDTO<List<ItemPQRSDTO>>> listarPQRS() throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, administradorServicio.listarPQRS())) ;
    }

    @GetMapping("/detalle-pqrs/")
    private ResponseEntity<MensajeDTO<DetallePQRSDTO>> verDetallePQRS(int codigo) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, administradorServicio.verDetallePQRS(codigo))) ;
    }

    @PutMapping("/responder-pqrs")
    private ResponseEntity<MensajeDTO<Integer>> responderPQRS(RegistroRespuestaDTO registroRespuestaDTO) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, administradorServicio.responderPQRS(registroRespuestaDTO))) ;
    }

    @PutMapping("/cambiar-estado-pqrs")
    private ResponseEntity<MensajeDTO<String>> cambiarEstadoPQRS(int codigoPQRS, EstadoPQRS estadoPQRS) throws Exception{
        administradorServicio.cambiarEstadoPQRS(codigoPQRS, estadoPQRS);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "El estado de la PQRS fue cambiado correctamente"));
    }

    @GetMapping("/citas")
    private ResponseEntity<MensajeDTO<List<ItemCitaAdminDTO>>> listarCitas() throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, administradorServicio.listarCitas())) ;
    }
}
