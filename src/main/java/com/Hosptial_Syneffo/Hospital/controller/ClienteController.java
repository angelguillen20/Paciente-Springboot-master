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

import com.Hosptial_Syneffo.Hospital.model.cliente;
import com.Hosptial_Syneffo.Hospital.service.ClienteService;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<cliente> listarPaciente(){
        return clienteService.getCliente();
    }
    @PostMapping
    public cliente agregarPaciente(@RequestBody cliente cliente){
        return clienteService.guaradarCliente(cliente);
    }
    @GetMapping("/rutPaciente/{rut}")
    public cliente buscarPorRut(@PathVariable String rut){
        return clienteService.getPacienteRut(rut);

    }

     @GetMapping("/nombrePaciente/{nombre}")
    public cliente buscarPornombre(@PathVariable String nombre){
        return clienteService.getPacienteNombre(nombre);

    }
    @PutMapping("/actualizar/{id}")
    public cliente actualizarPaciente(@PathVariable int id,@RequestBody cliente cliente){
        return clienteService.actualizarCliente(cliente);
    }
    @DeleteMapping("/eliminar/{nombre}")
    public String deletePacientePorNombre(@PathVariable String nombre){
        return clienteService.delateClientePorNombre(nombre);

    }
    @DeleteMapping("/eliminar/{rut}")
    public String deletePacienteRut(@PathVariable String rut){
        return clienteService.delateClientePorRut(rut);

    }

}


