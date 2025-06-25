package com.Hosptial_Syneffo.Hospital.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Hosptial_Syneffo.Hospital.model.Paciente;
import com.Hosptial_Syneffo.Hospital.repository.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public Paciente crearPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> obtenerPacientePorId(int id) {
        return pacienteRepository.findById(id);
    }

    public List<Paciente> obtenerTodosLosPacientes() {
        return pacienteRepository.findAll();
    }

    @Transactional
    public String actualizarPaciente(int id, Paciente nuevoPaciente) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);
        if (optionalPaciente.isPresent()) {
            Paciente existente = optionalPaciente.get();

            existente.setRunPaciente(nuevoPaciente.getRunPaciente());
            existente.setNombrePaciente(nuevoPaciente.getNombrePaciente());
            existente.setGenero(nuevoPaciente.getGenero());
            existente.setTipoSangre(nuevoPaciente.getTipoSangre());
            existente.setFechaNacimiento(nuevoPaciente.getFechaNacimiento());
            existente.setEstadoActual(nuevoPaciente.getEstadoActual());

            pacienteRepository.save(existente);
            return "Paciente actualizado correctamente";
        } else {
            return "Paciente no encontrado";
        }
    }

    @Transactional
    public String eliminarPaciente(int id) {
        Optional<Paciente> pacienteOpt = pacienteRepository.findById(id);
        if (pacienteOpt.isPresent()) {
            pacienteRepository.delete(pacienteOpt.get());
            return "Paciente eliminado correctamente";
        } else {
            return "Paciente no encontrado";
        }
    }
}

