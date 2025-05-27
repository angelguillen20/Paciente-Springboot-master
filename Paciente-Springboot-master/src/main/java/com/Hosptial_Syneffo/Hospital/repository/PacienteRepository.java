package com.Hosptial_Syneffo.Hospital.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.Hosptial_Syneffo.Hospital.model.Paciente;

@Repository
public class PacienteRepository {

    private List<Paciente> listaPacientes = new ArrayList<>();

    public List<Paciente> obtenerPacientes(){
        return listaPacientes;
    }

    public Paciente buscarPorRut(String rut){
        for (Paciente paciente : listaPacientes) {
            if (paciente.getRunPaciente() == rut) {
                return paciente;
            }
        }
        return null; 
    }

    public Paciente buscarPorNombre (String nombre){
        for (Paciente paciente : listaPacientes) {
            if (paciente.getNombrePaciente().equals(nombre)) {
                return paciente;
            }
        }
        return null; 
    }

    public Paciente guardarPaciente(Paciente pac){
        listaPacientes.add(pac);
        return pac;
    }

    public Paciente actualizar(Paciente pac){
        int id =0;
        int idPosicion = 0; 
        for (int i = 0; i < listaPacientes.size(); i++) {
            if (listaPacientes.get(i).getIdPaciente() == pac.getIdPaciente()) {
                id = pac.getIdPaciente();
                idPosicion=i;
            }
        }
        Paciente Paciente1 = new Paciente(); 
        Paciente1.setIdPaciente(id);     
        Paciente1.setRunPaciente(pac.getRunPaciente());
        Paciente1.setNombrePaciente(pac.getNombrePaciente());
     
        Paciente1.setGenero(pac.getGenero());
        Paciente1.setTipoSangre(pac.getTipoSangre());  
        Paciente1.setFechaNacimiento(pac.getFechaNacimiento());
        Paciente1.setEstadoActual(pac.getEstadoActual());
        listaPacientes.set(idPosicion,Paciente1);
        return Paciente1; 
        
    }

    
    public void eliminarPorNombre(String nombre){
        listaPacientes.removeIf(x -> x.getNombrePaciente() == nombre);

    }
    public void eliminarPorRut(String rut){
        listaPacientes.removeIf(x -> x.getRunPaciente() == rut);
    }




}
