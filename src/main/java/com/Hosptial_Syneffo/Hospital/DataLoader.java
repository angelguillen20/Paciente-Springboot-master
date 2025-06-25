package com.Hosptial_Syneffo.Hospital;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.Hosptial_Syneffo.Hospital.model.Paciente;
import com.Hosptial_Syneffo.Hospital.repository.PacienteRepository;

import net.datafaker.Faker;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private PacienteRepository pacienteRepository;
    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();


        //Generar paciente
        for (int i = 0; i < 5; i++) {
            Paciente paciente = new Paciente();
          
            paciente.setRunPaciente(faker.idNumber().valid());
            paciente.setNombrePaciente(faker.name().fullName());
            paciente.setGenero(faker.options().option("Masculino", "Femenino", "Otro"));
            paciente.setTipoSangre(faker.options().option("A+", "A-", "B+", "B-", "AB+","AB-","O+","O-"));
            paciente.setFechaNacimiento(new Date());
            paciente.setEstadoActual(faker.options().option("Critìco","Estable","Grave","Terminal","Agudo","Cronico","indeterminado"));
            pacienteRepository.save(paciente);
        }

    }
}
