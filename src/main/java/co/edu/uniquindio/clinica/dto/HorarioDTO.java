package co.edu.uniquindio.clinica.dto;

import co.edu.uniquindio.clinica.modelo.DiasSemana;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class HorarioDTO {

    Integer idHorario;

    LocalTime horaInicio;

    LocalTime horaFin;

    DiasSemana dia;

    public HorarioDTO(DiasSemana diasSemana, LocalTime horaInicio, LocalTime horaFinal) {
        this.dia = diasSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFinal;
    }
}
