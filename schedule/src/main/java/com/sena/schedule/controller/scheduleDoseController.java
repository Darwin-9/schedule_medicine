package com.sena.schedule.controller;

import com.sena.schedule.DTO.scheduleDoseDTO;
import com.sena.schedule.DTO.responseDTO;
import com.sena.schedule.service.scheduleDoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para la gestión de dosis agendadas.
 */
@RestController
@RequestMapping("/api/scheduledoses")
public class scheduleDoseController {

    @Autowired
    private scheduleDoseService service;

    /**
     * Crea una nueva dosis agendada.
     */
    @PostMapping
    public ResponseEntity<responseDTO> save(@RequestBody scheduleDoseDTO dto) {
        responseDTO response = service.save(dto);
        HttpStatus status = response.getStatus().equals("OK") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    /**
     * Lista todas las dosis agendadas.
     */
    @GetMapping
    public ResponseEntity<List<scheduleDoseDTO>> findAll() {
        List<scheduleDoseDTO> doses = service.findAll();
        return new ResponseEntity<>(doses, HttpStatus.OK);
    }

    /**
     * Busca una dosis agendada por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<scheduleDoseDTO> dto = service.findById(id);
        if (dto.isPresent()) {
            return new ResponseEntity<>(dto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new responseDTO("NOT_FOUND", "Dosis agendada no existe"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Actualiza una dosis agendada existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<responseDTO> update(@PathVariable int id, @RequestBody scheduleDoseDTO dto) {
        responseDTO response = service.update(id, dto);
        HttpStatus status = response.getStatus().equals("OK") ? HttpStatus.OK :
                response.getStatus().equals("NOT_FOUND") ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    /**
     * Elimina una dosis agendada por su ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<responseDTO> delete(@PathVariable int id) {
        responseDTO response = service.delete(id);
        HttpStatus status = response.getStatus().equals("OK") ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }
}