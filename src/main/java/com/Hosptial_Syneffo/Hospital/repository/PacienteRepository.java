package com.Hosptial_Syneffo.Hospital.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hosptial_Syneffo.Hospital.model.Paciente;
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

}
