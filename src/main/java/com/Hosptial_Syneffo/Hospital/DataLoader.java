package com.Hosptial_Syneffo.Hospital;

import java.util.Date;

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
        
        // Generar pacientes
        for (int i = 0; i < 15; i++) {
            Paciente paciente = new Paciente();
            
            // Datos básicos del paciente
            paciente.setRunPaciente(faker.number().digits(8) + "-" + faker.number().digit());
            paciente.setNombrePaciente(faker.name().fullName());
            paciente.setGenero(faker.options().option("Masculino", "Femenino", "Otro"));
            paciente.setTipoSangre(faker.options().option("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"));
            paciente.setFechaNacimiento(new Date());
            paciente.setEstadoActual(faker.options().option("Crítico", "Estable", "Grave", "Recuperación", "Observación"));
            
            // Datos de ubicación (opcionales)
            if (i % 3 != 0) { // Asignar cama a 2/3 de los pacientes
                paciente.setCamaId((long) faker.number().numberBetween(1, 50));
            }
            if (i % 2 == 0) { // Asignar habitación a la mitad de los pacientes
                paciente.setHabitacionId((long) faker.number().numberBetween(1, 20));
            }
            
            pacienteRepository.save(paciente);
        }
    }
}
