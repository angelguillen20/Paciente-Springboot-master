package com.Hosptial_Syneffo.Hospital.service;

import java.util.List;

import com.Hosptial_Syneffo.Hospital.model.Dto.CamasDto;
import com.Hosptial_Syneffo.Hospital.model.Paciente;
import com.Hosptial_Syneffo.Hospital.repository.PacienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${habitacion.service.url}")
    private String habitacionServiceUrl;

    @Transactional
    public Paciente crearPaciente(Paciente paciente) {
        if (paciente.getHabitacionId() != null) {
            String url = habitacionServiceUrl + "/" + paciente.getHabitacionId() + "/camas";
            CamasDto[] camasDisponibles = restTemplate.getForObject(url, CamasDto[].class);

            if (camasDisponibles != null && camasDisponibles.length > 0) {
                paciente.setCamaId(camasDisponibles[0].getId());
            } else {
                throw new RuntimeException("No hay camas disponibles en la habitación ID: " + paciente.getHabitacionId());
            }
        } else {
            throw new RuntimeException("El paciente debe tener una habitación asignada");
        }

        return pacienteRepository.save(paciente);
    }

    public Paciente obtenerPacientePorId(int id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    public List<Paciente> obtenerTodosLosPacientes1() {
        return pacienteRepository.findAll();
    }

    @Transactional
    public Paciente actualizarPaciente(Paciente paciente) {
       Paciente updatPaciente = obtenerPacientePorId(paciente.getIdPaciente());
        if (updatPaciente == null) {
            return null; // O lanzar una excepción si se prefiere
        }

        updatPaciente.setRunPaciente(paciente.getRunPaciente());
        updatPaciente.setNombrePaciente(paciente.getNombrePaciente());
        updatPaciente.setGenero(paciente.getGenero());
        updatPaciente.setTipoSangre(paciente.getTipoSangre());
        updatPaciente.setFechaNacimiento(paciente.getFechaNacimiento());
        updatPaciente.setEstadoActual(paciente.getEstadoActual());
        updatPaciente.setCamaId(paciente.getCamaId());
        updatPaciente.setHabitacionId(paciente.getHabitacionId());

        return pacienteRepository.save(updatPaciente);
    }

    @Transactional
    public void eliminarPaciente(int id) {
        pacienteRepository.deleteById(id);
    }
}


