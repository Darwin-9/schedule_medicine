package com.sena.schedule.controller;

import com.sena.schedule.DTO.medicationDTO;
import com.sena.schedule.DTO.responseDTO;
import com.sena.schedule.service.medicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para la gestión de medicamentos.
 */
@RestController
@RequestMapping("/api/medications")
public class medicationController {

    @Autowired
    private medicationService service;

    /**
     * Crea un nuevo medicamento.
     */
    @PostMapping
    public ResponseEntity<responseDTO> save(@RequestBody medicationDTO dto) {
        responseDTO response = service.save(dto);
        HttpStatus status = response.getStatus().equals("OK") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    /**
     * Lista todos los medicamentos.
     */
    @GetMapping
    public ResponseEntity<List<medicationDTO>> findAll() {
        List<medicationDTO> medications = service.findAll();
        return new ResponseEntity<>(medications, HttpStatus.OK);
    }

    /**
     * Busca un medicamento por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<medicationDTO> dto = service.findById(id);
        if (dto.isPresent()) {
            return new ResponseEntity<>(dto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new responseDTO("NOT_FOUND", "Medicamento no existe"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Actualiza un medicamento existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<responseDTO> update(@PathVariable int id, @RequestBody medicationDTO dto) {
        responseDTO response = service.update(id, dto);
        HttpStatus status = response.getStatus().equals("OK") ? HttpStatus.OK :
                response.getStatus().equals("NOT_FOUND") ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    /**
     * Elimina un medicamento por su ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<responseDTO> delete(@PathVariable int id) {
        responseDTO response = service.delete(id);
        HttpStatus status = response.getStatus().equals("OK") ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }
}