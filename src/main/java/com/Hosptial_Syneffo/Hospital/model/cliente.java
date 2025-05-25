package com.Hosptial_Syneffo.Hospital.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class cliente {
        private int idUsuario;
        private String runUsuario;
        private String pNombreUsuario;
        private String sNombreUsuario;
        private String aPaternoUsuario;
        private String aMaternoUsuario;
        private int telefono;
        
}
