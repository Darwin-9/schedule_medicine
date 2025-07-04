package com.sena.schedule.controller;

import com.sena.schedule.DTO.patientDTO;
import com.sena.schedule.DTO.responseDTO;
import com.sena.schedule.service.patientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para la gestión de pacientes.
 */
@RestController
@RequestMapping("/api/patients")
public class patientController {

    @Autowired
    private patientService service;

    /**
     * Crea un nuevo paciente.
     */
    @PostMapping
    public ResponseEntity<responseDTO> save(@RequestBody patientDTO dto) {
        responseDTO response = service.save(dto);
        HttpStatus status = response.getStatus().equals("OK") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    /**
     * Lista todos los pacientes.
     */
    @GetMapping
    public ResponseEntity<List<patientDTO>> findAll() {
        List<patientDTO> patients = service.findAll();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    /**
     * Busca un paciente por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<patientDTO> dto = service.findById(id);
        if (dto.isPresent()) {
            return new ResponseEntity<>(dto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new responseDTO("NOT_FOUND", "Paciente no existe"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Actualiza un paciente existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<responseDTO> update(@PathVariable int id, @RequestBody patientDTO dto) {
        responseDTO response = service.update(id, dto);
        HttpStatus status = response.getStatus().equals("OK") ? HttpStatus.OK :
                response.getStatus().equals("NOT_FOUND") ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    /**
     * Elimina un paciente por su ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<responseDTO> delete(@PathVariable int id) {
        responseDTO response = service.delete(id);
        HttpStatus status = response.getStatus().equals("OK") ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }
}