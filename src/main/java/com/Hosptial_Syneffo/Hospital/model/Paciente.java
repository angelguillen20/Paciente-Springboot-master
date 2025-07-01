
package com.Hosptial_Syneffo.Hospital.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "paciente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPaciente; 

    @Column(unique = true, length = 13, nullable = false)
    private String runPaciente;

    @Column(nullable = true)
    private String nombrePaciente;

    @Column(nullable = true)
    private String genero;

    @Column(nullable = true)
    private String tipoSangre; 

    @Column(nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd") 
    private Date fechaNacimiento; 

    @Column(nullable = true)
    private String estadoActual;

    @Column(nullable = true)
    private Long camaId;

    @Column(nullable = true)
    private Long habitacionId;


}
