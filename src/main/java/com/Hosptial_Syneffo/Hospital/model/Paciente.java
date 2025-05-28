package com.Hosptial_Syneffo.Hospital.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name="paciente")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPaciente; 

    @Column(unique=true, length = 13, nullable=false)
    private String runPaciente;

    @Column(nullable=true)
    private String nombrePaciente;

    @Column(nullable=true)
    private String genero;

    @Column(nullable=true)
    private String tipoSangre; 

    @Column(nullable=true)
   @JsonFormat(pattern = "yyyy-MM-dd") 
    private Date fechaNacimiento; 

    @Column(nullable=true)
    private String estadoActual;


}
