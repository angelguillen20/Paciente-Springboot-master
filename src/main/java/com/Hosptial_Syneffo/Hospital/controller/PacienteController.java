package com.Hosptial_Syneffo.Hospital.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.Hosptial_Syneffo.Hospital.model.Paciente;
import com.Hosptial_Syneffo.Hospital.service.PacienteService;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public Paciente crearPaciente(@RequestBody Paciente paciente) {
        return pacienteService.crearPaciente(paciente);
    }

    @GetMapping("/{id}")
    public Optional<Paciente> obtenerPacientePorId(@PathVariable int id) {
        return pacienteService.obtenerPacientePorId(id);
    }

    @GetMapping
    public List<Paciente> obtenerTodosLosPacientes() {
        return pacienteService.obtenerTodosLosPacientes();
    }

    @PutMapping("/{id}")
    public String actualizarPaciente(@PathVariable int id, @RequestBody Paciente paciente) {
        return pacienteService.actualizarPaciente(id, paciente);
    }

    @DeleteMapping("/{id}")
    public String eliminarPaciente(@PathVariable int id) {
        pacienteService.eliminarPaciente(id);
        return "Paciente eliminado correctamente";
    }
}

