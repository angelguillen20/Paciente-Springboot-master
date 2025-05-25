package com.Hosptial_Syneffo.Hospital.model;
import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class cliente {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idUsuario;

        @Column(length = 9, unique = true, nullable = false)
        private String runUsuario;
        
        @Column(nullable=true)
        private String nombreUsuario;

        @Column(nullable=true)
        private int telefono;

}
