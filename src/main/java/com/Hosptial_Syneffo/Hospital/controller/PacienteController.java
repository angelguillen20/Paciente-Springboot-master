package com.Hosptial_Syneffo.Hospital.controller;

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

import com.Hosptial_Syneffo.Hospital.model.Paciente;
import com.Hosptial_Syneffo.Hospital.service.PacienteService;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<Paciente> listarPaciente(){
        return pacienteService.getPaciente();
    }
    @PostMapping
    public Paciente agregarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guaradarPaciente(paciente);
    }
    @GetMapping("/rutPaciente/{rut}")
    public Paciente buscarPorRut(@PathVariable int rut){
        return pacienteService.getPacienteRut(rut);

    }

     @GetMapping("/nombrePaciente/{nombre}")
    public Paciente buscarPornombre(@PathVariable String nombre){
        return pacienteService.getPacienteNombre(nombre);

    }
    @PutMapping("/actualizar/{id}")
    public Paciente actualizarPaciente(@PathVariable int id,@RequestBody Paciente pac){
        return pacienteService.actualizarPaciente(pac);
    }
    @DeleteMapping("/eliminar/{nombre}")
    public String deletePacientePorNombre(@PathVariable String nombre){
        return pacienteService.delatePacientePorNombre(nombre);

    }
    @DeleteMapping("/eliminar/{rut}")
    public String deletePacienteRut(@PathVariable int rut){
        return pacienteService.delatePacientePorRut(rut);

    }

}
