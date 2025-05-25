package com.Hosptial_Syneffo.Hospital.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Paciente {
    private int idPaciente; 
    private String runPaciente;
    private String pNombrePaciente;
    private String sNombrePaciente;
    private String aPaternoPaciente;
    private String genero;
    private String tipoSangre; 
    private Date fechaNacimiento; 
    private String estadoActual;



}
