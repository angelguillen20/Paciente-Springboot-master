package com.Habitaciones.Habitacion.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "habitaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Habitacion {

    @Id
    private int id;
    private String NumeroHabit; //Numero de la Habitacion
    private String TipoHabitacion;
    private int CapacidadMax;
    //public Cama cama;

    @OneToMany(mappedBy = "habitacion",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Camas> camas;
}
