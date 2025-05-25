package com.Hosptial_Syneffo.Hospital.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Paciente {
    private int id; 
    private int rut;
    private String nombrePaciente;
    private String estadoPaciente;
    private String descripPaciente; 
    private Date fechaNacimiento; 
    private String seguroPaciente;

}
