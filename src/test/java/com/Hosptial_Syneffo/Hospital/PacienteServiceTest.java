package com.Hosptial_Syneffo.Hospital;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.Hosptial_Syneffo.Hospital.model.Paciente;
import com.Hosptial_Syneffo.Hospital.repository.PacienteRepository;
import com.Hosptial_Syneffo.Hospital.service.PacienteService;

@SpringBootTest
public class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteService pacienteService;

    @Test
    void testFindAll() {
        Paciente paciente = new Paciente();
        paciente.setIdPaciente(1);
        paciente.setRunPaciente("12345678-9");
        paciente.setNombrePaciente("Juan Pérez");

        when(pacienteRepository.findAll()).thenReturn(List.of(paciente));

        List<Paciente> pacientes = pacienteService.obtenerTodosLosPacientes();

        assertNotNull(pacientes);
        assertEquals(1, pacientes.size());
    }
    
    @Test
    void testObtenerPacientePorId_Existe() {
        Paciente paciente = new Paciente();
        paciente.setIdPaciente(1);
        when(pacienteRepository.findById(1)).thenReturn(Optional.of(paciente));

        Optional<Paciente> result = pacienteService.obtenerPacientePorId(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getIdPaciente());
    }

    @Test
    void testObtenerPacientePorId_NoExiste() {
        when(pacienteRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Paciente> result = pacienteService.obtenerPacientePorId(99);

        assertFalse(result.isPresent());
    }
    @Test
    void testCrearPaciente() {
        Paciente paciente = new Paciente();
        paciente.setRunPaciente("12345678-9");
        paciente.setNombrePaciente("Juan Pérez");
        paciente.setGenero("Masculino");
        paciente.setTipoSangre("O+");
        java.util.Date fechaNacimiento = java.util.Date.from(LocalDate.of(1990, 1, 1).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
        paciente.setFechaNacimiento(fechaNacimiento);
        paciente.setEstadoActual("Estable");

        when(pacienteRepository.save(paciente)).thenReturn(paciente);

        Paciente result = pacienteService.crearPaciente(paciente);

        assertNotNull(result);
        assertEquals("12345678-9", result.getRunPaciente());
    }
    @Test
    void testActualizarPaciente_Existe() {
        Paciente pacienteExistente = new Paciente();
        pacienteExistente.setIdPaciente(1);
        pacienteExistente.setRunPaciente("12345678-9");
        pacienteExistente.setNombrePaciente("Juan Pérez");    
        pacienteExistente.setGenero("Masculino");
        pacienteExistente.setTipoSangre("O+");     
        java.util.Date fechaNacimiento = java.util.Date.from(LocalDate.of(1990, 1, 1).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
        pacienteExistente.setFechaNacimiento(fechaNacimiento);
        pacienteExistente.setEstadoActual("Estable");
        when(pacienteRepository.findById(1)).thenReturn(Optional.of(pacienteExistente));
        Paciente pacienteActualizado = new Paciente();
        pacienteActualizado.setRunPaciente("98765432-1");
        pacienteActualizado.setNombrePaciente("Pedro Gómez");
        pacienteActualizado.setGenero("Masculino");
        pacienteActualizado.setTipoSangre("A+");
        pacienteActualizado.setFechaNacimiento(fechaNacimiento);
        pacienteActualizado.setEstadoActual("Grave");
        when(pacienteRepository.save(pacienteExistente)).thenReturn(pacienteActualizado);
        String resultado = pacienteService.actualizarPaciente(1, pacienteActualizado);
        assertEquals("Paciente actualizado correctamente", resultado);
        assertEquals("98765432-1", pacienteExistente.getRunPaciente());
        assertEquals("Pedro Gómez", pacienteExistente.getNombrePaciente());
        assertEquals("Masculino", pacienteExistente.getGenero());
        assertEquals("A+", pacienteExistente.getTipoSangre());
        assertEquals(fechaNacimiento, pacienteExistente.getFechaNacimiento());
        assertEquals("Grave", pacienteExistente.getEstadoActual());
     }
    @Test
    void testActualizarPaciente_NoExiste() {
        Paciente pacienteActualizado = new Paciente();
        pacienteActualizado.setRunPaciente("98765432-1");
        pacienteActualizado.setNombrePaciente("Pedro Gómez");
        pacienteActualizado.setGenero("Masculino");
        pacienteActualizado.setTipoSangre("A+");
        java.util.Date fechaNacimiento = java.util.Date.from(LocalDate.of(1990, 1, 1).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
        pacienteActualizado.setFechaNacimiento(fechaNacimiento);
        pacienteActualizado.setEstadoActual("Grave");

        when(pacienteRepository.findById(99)).thenReturn(Optional.empty());

        String resultado = pacienteService.actualizarPaciente(99, pacienteActualizado);

        assertEquals("Paciente no encontrado", resultado);
    }  
    @Test
    void testEliminarPaciente_Existe() {
        Paciente paciente = new Paciente();
        paciente.setIdPaciente(1);
        when(pacienteRepository.findById(1)).thenReturn(Optional.of(paciente));

        String resultado = pacienteService.eliminarPaciente(1);

        assertEquals("Paciente eliminado correctamente", resultado);
    }
    @Test
    void testEliminarPaciente_NoExiste() {
        when(pacienteRepository.findById(99)).thenReturn(Optional.empty());

        String resultado = pacienteService.eliminarPaciente(99);

        assertEquals("Paciente no encontrado", resultado);
    }
    
}


