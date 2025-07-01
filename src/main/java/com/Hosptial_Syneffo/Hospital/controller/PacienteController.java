package com.Hosptial_Syneffo.Hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Hosptial_Syneffo.Hospital.model.Paciente;
import com.Hosptial_Syneffo.Hospital.service.PacienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/pacientes")
@Tag(name = "Pacientes", description = "API para gestión de pacientes hospitalarios")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;


    // Crear paciente
    @PostMapping
    @Operation(summary = "Crear paciente", description = "Registra un nuevo paciente en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Paciente creado exitosamente",
            content = @Content(schema = @Schema(implementation = Paciente.class))),
        @ApiResponse(responseCode = "400", description = "Datos del paciente inválidos")
    })
    public ResponseEntity<Paciente> crearPaciente(@RequestBody Paciente paciente) {
        try {
            Paciente nuevoPaciente = pacienteService.crearPaciente(paciente);
            if (nuevoPaciente != null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(nuevoPaciente);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    // Obtener paciente por ID

    @GetMapping("/{id}")
    @Operation(summary = "Obtener paciente por ID", description = "Recupera la información de un paciente específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Paciente encontrado",
            content = @Content(schema = @Schema(implementation = Paciente.class))),
        @ApiResponse(responseCode = "404", description = "Paciente no encontrado"),

    })
    public ResponseEntity<Paciente> obtenerPacientePorId(@PathVariable( "id") int id) {
        try {
            Paciente paciente = pacienteService.obtenerPacientePorId(id);
            if (paciente == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(paciente);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Listar todos los pacientes

    @GetMapping
    @Operation(summary = "Listar todos los pacientes", description = "Obtiene un listado completo de pacientes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pacientes obtenida",
            content = @Content(schema = @Schema(implementation = Paciente.class))),
        @ApiResponse(responseCode = "204", description = "No hay pacientes registrados")
    })
    public ResponseEntity<List<Paciente>> obtenerTodosLosPacientes() {
       try {
            List<Paciente> pacientes = pacienteService.obtenerTodosLosPacientes1();
            if (pacientes.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(pacientes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    // Actualizar paciente

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar paciente", description = "Actualiza la información de un paciente existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Paciente actualizado exitosamente",
            content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "404", description = "Paciente no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        
    })
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente) {
        try {
            Paciente updatedPaciente = pacienteService.actualizarPaciente(paciente);
            if (updatedPaciente == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedPaciente);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    // Eliminar paciente
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar paciente", description = "Elimina un paciente del sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Paciente eliminado exitosamente",
            content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "404", description = "Paciente no encontrado"),

    })
    public ResponseEntity<Paciente> eliminarPaciente(@PathVariable("id") int id
) {
        try {
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}