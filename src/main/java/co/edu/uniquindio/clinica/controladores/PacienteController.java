package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.dto.*;
import co.edu.uniquindio.clinica.modelo.Paciente;
import co.edu.uniquindio.clinica.modelo.SolicitarCitaDTO;
import co.edu.uniquindio.clinica.servicios.interfaces.PacienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteServicio pacienteServicio;

    @GetMapping("/registrarse")
    private ResponseEntity<MensajeDTO<String>> registrarse(RegistroDTO registroDTO) throws Exception{
        pacienteServicio.registrarse(registroDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Paciente registrado correctamente"));
    }

    @PutMapping("/editar-perfil")
    private ResponseEntity<MensajeDTO<String>> editarPerfil(ActualizarDTO actualizarDTO) throws Exception{
        pacienteServicio.editarPerfil(actualizarDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Paciente editado correctamente"));

    }

    @DeleteMapping("/eliminar")
    private ResponseEntity<MensajeDTO<String>> eliminarCuenta(Integer idPaciente) throws Exception{
        pacienteServicio.eliminarCuenta(idPaciente);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Paciente eliminado correctamente"));
    }

    @GetMapping("/ver-detalle")
    private ResponseEntity<MensajeDTO<Paciente>> verDetallePaciente (Integer idPaciente) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                pacienteServicio.verDetallePaciente(idPaciente)) );
    }

    @PostMapping("/pedir-cita")
    private ResponseEntity<MensajeDTO<String>> pedirCita (SolicitarCitaDTO solicitarCitaDTO) throws Exception{
        pacienteServicio.pedirCita(solicitarCitaDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Cita registrada correctamente"));
    }

    @GetMapping("/consultar-cita")
    private ResponseEntity<MensajeDTO<CitaDTO>> consultarCita (ConsultarCitaDTO consultarCitaDTO) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                pacienteServicio.consultarCita(consultarCitaDTO)));
    }

    @PutMapping("/pagar-factura")
    private ResponseEntity<MensajeDTO<Paciente>> pagarFactura (Integer idPaciente) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                pacienteServicio.pagarFactura(idPaciente)));
    }

    @GetMapping("/listar-citas")
    private ResponseEntity<MensajeDTO<List<CitaDTO>>> listarCitas(Integer idPaciente) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                pacienteServicio.listarCitas(idPaciente)));
    }

    @GetMapping("/consultar-disponibilidad")
    private ResponseEntity<MensajeDTO<String>> consultarDisponibilidad(SolicitarCitaDTO solicitarCitaDTO) throws Exception{
        pacienteServicio.consultarDisponibilidad(solicitarCitaDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Disponibilidad consultada correctamente"));
    }
}
