package com.Hosptial_Syneffo.Hospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Hosptial_Syneffo.Hospital.model.cliente;
import com.Hosptial_Syneffo.Hospital.repository.ClienteRepository;


@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    
    public List<cliente> getCliente(){
        return clienteRepository.obtenerCliente();
    }
    public cliente guaradarCliente(cliente cliente){
        return clienteRepository.guardarCliente(cliente);
    }
    public cliente getPacienteRut(String rut){
        return clienteRepository.buscarPorRut(rut);
    }
    public cliente getPacienteNombre(String nombre){
        return clienteRepository.buscarPorNombre(nombre);
    }
    public cliente actualizarCliente(cliente cli){
        return clienteRepository.actualizar(cli);
    }
    public String delateClientePorNombre(String nombre){
        clienteRepository.eliminarPorNombre(nombre);;
        return "cliente eliminado";
    }
    public String delateClientePorRut(String rut){
        clienteRepository.eliminarPorRut(rut);
        return "cliente eliminado";
    }
}
