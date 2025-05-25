package com.Hosptial_Syneffo.Hospital.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;


import com.Hosptial_Syneffo.Hospital.model.cliente;

@Repository
public class ClienteRepository  {
    private List<cliente> listacClientes = new ArrayList<>();

    public List<cliente> obtenerCliente(){
        return listacClientes;
    }

    public cliente buscarPorRut(String rut){
        for (cliente cliente : listacClientes) {
            if (cliente.getRunUsuario() == rut) {
                return cliente;
            }
        }
        return null; 
    }

    public cliente buscarPorNombre (String nombre){
        for (cliente cliente : listacClientes) {
            if (cliente.getNombreUsuario().equals(nombre)) {
                return cliente;
            }
        }
        return null; 
    }

    public cliente guardarCliente(cliente cli){
        listacClientes.add(cli);
        return cli;
    }

    public cliente actualizar(cliente cli){
        int id =0;
        int idPosicionUsuario = 0; 
        for (int i = 0; i < listacClientes.size(); i++) {
            if (listacClientes.get(i).getIdUsuario() == cli.getIdUsuario()) {
                id = cli.getIdUsuario();
                idPosicionUsuario=i;
            }
        }
        cliente cli1 = new cliente(); 
        cli1.setIdUsuario(id);     
        cli1.setRunUsuario(cli.getRunUsuario());
        cli1.setNombreUsuario(cli.getNombreUsuario());
        cli1.setTelefono(cli.getTelefono());
       
        return cli1; 
        
    }

    
    public void eliminarPorNombre(String nombre){
        listacClientes.removeIf(x -> x.getNombreUsuario() == nombre);

    }
    public void eliminarPorRut(String rut){
        listacClientes.removeIf(x -> x.getRunUsuario() == rut);
    }





}
