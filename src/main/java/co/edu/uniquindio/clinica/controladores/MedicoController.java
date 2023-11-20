package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.dto.*;
import co.edu.uniquindio.clinica.servicios.interfaces.MedicoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoServicio medicoServicio;

    @PostMapping("/solicitar-dia-libre")
    private ResponseEntity<MensajeDTO<String>> solicitarDiaLibre(SolicitarDiaLibreInDTO solicitudDiaLibre) throws Exception{
        medicoServicio.solicitarDiaLibre(solicitudDiaLibre);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Dia libre solicitado correctamente"));
    }

    @GetMapping("/consultar-citas-pendientes")
    private ResponseEntity<MensajeDTO<List<CitaDTO>>> consultarCitasPendientes (Integer idEstadoCita, Integer idMedico) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                medicoServicio.consultarCitasPendientes(idEstadoCita, idMedico)));
    }

    @PostMapping("/historico-citas")
    private ResponseEntity<MensajeDTO<List<AtencionDTO>>> consultarHistorialCitasAtendidas (Integer idMedico) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                medicoServicio.consultarHistorialPaciente(idMedico)));
    }

    @PostMapping("/historial-paciente")
    private ResponseEntity<MensajeDTO<List<AtencionDTO>>> consultarHistorialPaciente (Integer idPaciente) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                medicoServicio.consultarHistorialPaciente(idPaciente)));
    }

    @PostMapping("/diagnostico-paciente")
    private ResponseEntity<MensajeDTO<String>> realizarDiagnosticoPaciente (AtencionCitaDTO atencionCitaDTO) throws Exception{
        medicoServicio.realizarDiagnosticoPaciente(atencionCitaDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Atencion agregada correctamente"));
    }

    @PutMapping("/reserva-quirofano")
    private ResponseEntity<MensajeDTO<String>> solicitarReservaQuirofano (SolicitarReservaQuirofanoDTO solicitudReserva) throws Exception{
        medicoServicio.solicitarReservaQuirofano(solicitudReserva);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Solicitud creada correctamente"));
    }
}
