package com.Hosptial_Syneffo.Hospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Hosptial_Syneffo.Hospital.model.Paciente;
import com.Hosptial_Syneffo.Hospital.repository.PacienteRepository;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;
    
    public List<Paciente> getPaciente(){
        return pacienteRepository.obtenerPacientes();
    }
    public Paciente guaradarPaciente(Paciente pac){
        return pacienteRepository.guardarPaciente(pac);
    }
    public Paciente getPacienteRut(int rut){
        return pacienteRepository.buscarPorRut(rut);
    }
    public Paciente getPacienteNombre(String nombre){
        return pacienteRepository.buscarPorNombre(nombre);
    }
    public Paciente actualizarPaciente(Paciente pac){
        return pacienteRepository.actualizar(pac);
    }
    public String delatePacientePorNombre(String nombre){
        pacienteRepository.eliminarPorNombre(nombre);;
        return "Paciente eliminado";
    }
    public String delatePacientePorRut(int rut){
        pacienteRepository.eliminarPorRut(rut);
        return "Paciente eliminado";
    }




}
