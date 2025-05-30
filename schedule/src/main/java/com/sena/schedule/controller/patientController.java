package com.sena.schedule.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import com.sena.schedule.model.patient;
import com.sena.schedule.DTO.patientDTO;
import com.sena.schedule.DTO.responseDTO;
import com.sena.schedule.service.patientService;

@RestController
@RequestMapping("/api/v1/patients")
public class patientController {

    @Autowired
    private patientService patientService;

    // Registrar un nuevo paciente
    @PostMapping("/")
    public ResponseEntity<responseDTO> register(@RequestBody patientDTO patientDTO) {
        responseDTO response = patientService.save(patientDTO);
        if (response.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    // Obtener todos los pacientes
    @GetMapping("/")
    public ResponseEntity<List<patient>> getAll() {
        return new ResponseEntity<>(patientService.findAll(), HttpStatus.OK);
    }

    // Obtener un paciente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        Optional<patient> patient = patientService.findById(id);
        if (!patient.isPresent()) {
            return new ResponseEntity<>(new responseDTO(HttpStatus.NOT_FOUND.toString(), "Paciente no encontrado"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patient.get(), HttpStatus.OK);
    }

    // Actualizar un paciente
    @PutMapping("/{id}")
    public ResponseEntity<responseDTO> update(@PathVariable int id, @RequestBody patientDTO patientDTO) {
        responseDTO response = patientService.update(id, patientDTO);
        if (response.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Eliminar un paciente
    @DeleteMapping("/{id}")
    public ResponseEntity<responseDTO> delete(@PathVariable int id) {
        responseDTO response = patientService.delete(id);
        if (response.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}