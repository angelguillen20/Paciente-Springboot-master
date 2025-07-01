package com.Hosptial_Syneffo.Hospital.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.Hosptial_Syneffo.Hospital.model.Dto.CamasDto;
import com.Hosptial_Syneffo.Hospital.model.Paciente;
import com.Hosptial_Syneffo.Hospital.repository.PacienteRepository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PacienteService pacienteService;

    private Paciente paciente;

    @BeforeEach
    void setUp() {
        paciente = new Paciente();
        paciente.setIdPaciente(1);
        paciente.setHabitacionId(1L);
    }

    @Test
    public void crearPaciente_ConHabitacionValida_DeberiaRetornarPaciente() {
        // Arrange
        CamasDto[] camas = {new CamasDto()};
        when(restTemplate.getForObject(anyString(), eq(CamasDto[].class))).thenReturn(camas);
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        // Act
        Paciente result = pacienteService.crearPaciente(paciente);

        // Assert
        assertNotNull(result);
        verify(pacienteRepository).save(paciente);
    }

    @Test
    public void crearPaciente_SinHabitacion_DeberiaLanzarExcepcion() {
        // Arrange
        paciente.setHabitacionId(null);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            pacienteService.crearPaciente(paciente);
        });
    }

    @Test
    public void obtenerPacientePorId_Existente_DeberiaRetornarPaciente() {
        // Arrange
        when(pacienteRepository.findById(1)).thenReturn(Optional.of(paciente));

        // Act
        Paciente result = pacienteService.obtenerPacientePorId(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getIdPaciente());
    }

    @Test
    public void obtenerPacientePorId_NoExistente_DeberiaRetornarNull() {
        // Arrange
        when(pacienteRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        Paciente result = pacienteService.obtenerPacientePorId(1);

        // Assert
        assertNull(result);
    }

    @Test
    public void obtenerTodosLosPacientes_DeberiaRetornarLista() {
        // Arrange
        List<Paciente> pacientes = Arrays.asList(new Paciente(), new Paciente());
        when(pacienteRepository.findAll()).thenReturn(pacientes);

        // Act
        List<Paciente> result = pacienteService.obtenerTodosLosPacientes1();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    public void actualizarPaciente_Existente_DeberiaRetornarActualizado() {
        // Arrange
        Paciente pacienteActualizado = new Paciente();
        pacienteActualizado.setIdPaciente(1);
        pacienteActualizado.setNombrePaciente("Nuevo Nombre");

        when(pacienteRepository.findById(1)).thenReturn(Optional.of(paciente));
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(pacienteActualizado);

        // Act
        Paciente result = pacienteService.actualizarPaciente(pacienteActualizado);

        // Assert
        assertEquals("Nuevo Nombre", result.getNombrePaciente());
    }

    @Test
    public void actualizarPaciente_NoExistente_DeberiaRetornarNull() {
        // Arrange
        when(pacienteRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        Paciente result = pacienteService.actualizarPaciente(paciente);

        // Assert
        assertNull(result);
    }

    @Test
    public void eliminarPaciente_DeberiaInvocarDelete() {
        // Arrange
        doNothing().when(pacienteRepository).deleteById(1);

        // Act
        pacienteService.eliminarPaciente(1);

        // Assert
        verify(pacienteRepository).deleteById(1);
    }
}
