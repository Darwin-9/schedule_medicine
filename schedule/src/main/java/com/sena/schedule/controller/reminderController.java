package com.sena.schedule.controller;

import com.sena.schedule.DTO.reminderDTO;
import com.sena.schedule.DTO.responseDTO;
import com.sena.schedule.model.reminder;
import com.sena.schedule.repository.IReminder;
import com.sena.schedule.service.reminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para la gestión de recordatorios.
 */
@RestController
@RequestMapping("/api/reminders")
public class reminderController {

    @Autowired
    private reminderService service;

    @Autowired
    private IReminder reminderRepo;

    /**
     * Crea un nuevo recordatorio.
     */
    @PostMapping
    public ResponseEntity<responseDTO> save(@RequestBody reminderDTO dto) {
        responseDTO response = service.save(dto);
        HttpStatus status = response.getStatus().equals("OK") ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    /**
     * Lista todos los recordatorios.
     */
    @GetMapping
    public ResponseEntity<List<reminderDTO>> findAll() {
        List<reminderDTO> reminders = service.findAll();
        return new ResponseEntity<>(reminders, HttpStatus.OK);
    }

    /**
     * Busca un recordatorio por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<reminderDTO> dto = service.findById(id);
        if (dto.isPresent()) {
            return new ResponseEntity<>(dto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new responseDTO("NOT_FOUND", "Recordatorio no existe"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Actualiza un recordatorio existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<responseDTO> update(@PathVariable int id, @RequestBody reminderDTO dto) {
        responseDTO response = service.update(id, dto);
        HttpStatus status = response.getStatus().equals("OK") ? HttpStatus.OK :
                response.getStatus().equals("NOT_FOUND") ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    /**
     * Elimina un recordatorio por su ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<responseDTO> delete(@PathVariable int id) {
        responseDTO response = service.delete(id);
        HttpStatus status = response.getStatus().equals("OK") ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @PutMapping("/{id}/suspend")
public ResponseEntity<responseDTO> suspend(@PathVariable int id) {
    Optional<reminder> opt = reminderRepo.findById(id);
    if (opt.isPresent()) {
        reminder r = opt.get();
        r.setActive(false);
        reminderRepo.save(r);
        return ResponseEntity.ok(new responseDTO("OK", "Recordatorio suspendido"));
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new responseDTO("NOT_FOUND", "Recordatorio no existe"));
}

@PutMapping("/{id}/activate")
public ResponseEntity<responseDTO> activate(@PathVariable int id) {
    Optional<reminder> opt = reminderRepo.findById(id);
    if (opt.isPresent()) {
        reminder r = opt.get();
        r.setActive(true);
        reminderRepo.save(r);
        return ResponseEntity.ok(new responseDTO("OK", "Recordatorio reactivado"));
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new responseDTO("NOT_FOUND", "Recordatorio no existe"));
}


}