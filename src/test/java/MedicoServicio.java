import co.edu.uniquindio.clinica.dto.*;
import co.edu.uniquindio.clinica.modelo.EstadoReserva;
import co.edu.uniquindio.clinica.modelo.Medico;
import co.edu.uniquindio.clinica.modelo.Quirofano;
import co.edu.uniquindio.clinica.repositorios.*;
import co.edu.uniquindio.clinica.servicios.impl.MedicoServicioImpl;
import jakarta.transaction.Transactional;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class MedicoServicio {

    private MedicoServicioImpl medicoServicio;

    @Mock
    private MedicoRepo medicoRepo;

    @Mock
    private DiaLibreRepo diaLibreRepo;

    @Mock
    private EstadoCitaRepo estadoCitaRepo;

    @Mock
    private CitaRepo citaRepo;

    @Mock
    private QuirofanoRepo quirofanoRepo;

    @Mock
    private EstadoReservaRepo estadoReservaRepo;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        medicoServicio = new MedicoServicioImpl(medicoRepo, diaLibreRepo, estadoCitaRepo, citaRepo);
    }

    @org.junit.Test
    public void solicitarDiaLibre() throws Exception {
        // Configurar el escenario de prueba
        SolicitarDiaLibreInDTO solicitudDiaLibre = new SolicitarDiaLibreInDTO();
        solicitudDiaLibre.setIdMedico(1);
        solicitudDiaLibre.setFechaSolicitudDiaLibre("2023-10-19");

        Medico medico = new Medico();
        medico.setIdCuenta(1);

        when(medicoRepo.findById(1)).thenReturn(Optional.of(medico));
        when(diaLibreRepo.findByFechaMedico("2023-10-19", 1)).thenReturn(null);

        // Realizar la prueba
        ResultadoDTO resultado = MedicoServicioImpl.solicitarDiaLibre(solicitudDiaLibre);

        // Verificar que la solicitud fue exitosa
        assertTrue(resultado.setEsExitoso(true));
    }

    @Test
    public void testConsultarCitasPendientes() throws Exception {
        // Configurar el escenario de prueba
        Integer idEstadoCita = 1;
        Integer idMedico = 2;

        EstadoCitaDTO estadoCitaDTO = new EstadoCitaDTO();
        estadoCitaDTO.setEstado("PENDIENTE");

        List<CitaDTO> citas = new ArrayList<>(); // Agrega citas de ejemplo aquí

        when(estadoCitaRepo.findByCodigo("PENDIENTE")).thenReturn(estadoCitaDTO);
        when(citaRepo.findByEstadoCitaMedico(estadoCitaDTO.getIdEstadoCita(), idMedico)).thenReturn(citas);

        // Realizar la prueba
        List<CitaDTO> citasPendientes = MedicoServicioImpl.consultarCitasPendientes(estadoCitaDTO.getIdEstadoCita(), idMedico);

        // Verificar que se obtuvieron citas pendientes
        assertFalse(citasPendientes.isEmpty());
    }

    @Test
    public void testConsultarHistorialCitasAtendidas() throws Exception {
        // Configurar el escenario de prueba
        Integer idMedico = 3;

        List<CitaDTO> citas = new ArrayList<>(); // Agrega citas de ejemplo aquí

        when(citaRepo.findByMedico(idMedico)).thenReturn(citas);

        // Realizar la prueba
        List<CitaDTO> historialCitas = MedicoServicioImpl.consultarHistorialCitasAtendidas(idMedico);

        // Verificar que se obtuvo un historial de citas
        assertFalse(historialCitas.isEmpty());
    }

    @Test
    public void testSolicitarReservaQuirofano() throws Exception {
        // Configurar el escenario de prueba
        SolicitarReservaQuirofanoDTO solicitudReserva = new SolicitarReservaQuirofanoDTO();
        solicitudReserva.setIdMedico(1);
        solicitudReserva.setIdQuirofano(2);
        solicitudReserva.setFechaSolicitudReserva("2023-10-19");

        Medico medico = new Medico();
        medico.setIdCuenta(1);
        Quirofano quirofano = new Quirofano();
        quirofano.setIdQuirofano(2);
        EstadoReserva estadoReserva = new EstadoReserva();

        when(medicoRepo.findById(1)).thenReturn(Optional.of(medico));
        when(quirofanoRepo.findById(2)).thenReturn(Optional.of(quirofano));
        when(estadoReservaRepo.findByEstado("GENERADA")).thenReturn(estadoReserva);

        // Realizar la prueba
        ResultadoDTO resultado = MedicoServicioImpl.solicitarReservaQuirofano(solicitudReserva);

        // Verificar que la solicitud fue exitosa
        assertTrue(resultado.setEsExitoso(true));
    }
}
