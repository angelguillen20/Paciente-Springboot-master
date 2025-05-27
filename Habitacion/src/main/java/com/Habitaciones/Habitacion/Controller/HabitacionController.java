package com.Habitaciones.Habitacion.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Habitaciones.Habitacion.Model.Camas;
import com.Habitaciones.Habitacion.Model.Habitacion;
import com.Habitaciones.Habitacion.Service.HabitacionService;



@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

    @GetMapping
    public List<Habitacion> obtenerHabitaciones(){
        return habitacionService.obtenerHabitacion();
    }

    @GetMapping("/{id}")
    public Habitacion buscarHabitacion(@PathVariable int id){
        return habitacionService.buscarPorId(id);
    }

    @PostMapping
    public void agregarHabitacion(@RequestBody Habitacion habitacion){
        habitacionService.agregarHabitacion(habitacion);
    }

    @PutMapping
    public void actualizarHabitacion(@RequestBody Habitacion habitacion){
        habitacionService.actualizarHabitacion(habitacion);
    }
    @DeleteMapping("/{id}")
    public void eliminarHabitacion(@PathVariable int id){
        habitacionService.eliminarHabitacion(id);
    }
    @GetMapping("/{id}/camas")
    public List<Camas> obtenerCamas(@PathVariable int id){
        return habitacionService.obtenerCamasDeHabitacion(id);
    }
    @PutMapping("/{id}/camas/{camaid}")
    public boolean eliminarCamas(@PathVariable int id,@PathVariable long camaid){
        return habitacionService.eliminarCamasHabitacion(id, camaid);
    }
}
