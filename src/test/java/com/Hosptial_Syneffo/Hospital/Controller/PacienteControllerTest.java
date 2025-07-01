package com.Hosptial_Syneffo.Hospital.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.Hosptial_Syneffo.Hospital.model.Paciente;
import com.Hosptial_Syneffo.Hospital.service.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PacienteControllerTest.class)
public class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PacienteService pacienteService;

    @Autowired
    private ObjectMapper objectMapper;

    private Paciente paciente;

    @BeforeEach
    void setUp() {
        paciente = new Paciente();
        paciente.setIdPaciente(1);
        paciente.setNombrePaciente("Juan Pérez");
        paciente.setRunPaciente("12345678-9");
    }

    @Test
    public void testGetAllPacientes() throws Exception {
        when(pacienteService.obtenerTodosLosPacientes1()).thenReturn(List.of(paciente));

        mockMvc.perform(get("/api/v1/pacientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idPaciente").value(1))
                .andExpect(jsonPath("$[0].nombrePaciente").value("Juan Pérez"));
    }

    @Test
    public void testGetPacienteById() throws Exception {
        when(pacienteService.obtenerPacientePorId(1)).thenReturn(paciente);

        mockMvc.perform(get("/api/v1/pacientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPaciente").value(1))
                .andExpect(jsonPath("$.nombrePaciente").value("Juan Pérez"));
    }

    @Test
    public void testCreatePaciente() throws Exception {
        when(pacienteService.crearPaciente(any(Paciente.class))).thenReturn(paciente);

        mockMvc.perform(post("/api/v1/pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(paciente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPaciente").value(1))
                .andExpect(jsonPath("$.nombrePaciente").value("Juan Pérez"));
    }

    @Test
    public void testUpdatePaciente() throws Exception {
        when(pacienteService.actualizarPaciente(any(Paciente.class))).thenReturn(paciente);

        mockMvc.perform(put("/api/v1/pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(paciente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPaciente").value(1))
                .andExpect(jsonPath("$.nombrePaciente").value("Juan Pérez"));
    }

    @Test
    public void testDeletePaciente() throws Exception {
        doNothing().when(pacienteService).eliminarPaciente(1);

        mockMvc.perform(delete("/api/v1/pacientes/1"))
                .andExpect(status().isNoContent());
        
        verify(pacienteService, times(1)).eliminarPaciente(1);
    }

    @Test
    public void testGetAllPacientesEmpty() throws Exception {
        when(pacienteService.obtenerTodosLosPacientes1()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/pacientes"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetPacienteByIdNotFound() throws Exception {
        when(pacienteService.obtenerPacientePorId(1)).thenReturn(null);

        mockMvc.perform(get("/api/v1/pacientes/1"))
                .andExpect(status().isNotFound());
    }
}