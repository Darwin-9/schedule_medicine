package com.sena.schedule.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.sena.schedule.model.reminder;
import com.sena.schedule.DTO.reminderDTO;
import com.sena.schedule.DTO.responseDTO;
import com.sena.schedule.service.reminderService;

@RestController
@RequestMapping("/api/v1/reminders")
public class reminderController {

    @Autowired
    private reminderService reminderService;

    // Create a new reminder
    @PostMapping("/")
    public ResponseEntity<responseDTO> create(@RequestBody reminderDTO reminderDTO) {
        responseDTO response = reminderService.save(reminderDTO);
        if (response.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    // Get all reminders
    @GetMapping("/")
    public ResponseEntity<List<reminder>> getAll() {
        return new ResponseEntity<>(reminderService.findAll(), HttpStatus.OK);
    }

    // Get reminder by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        Optional<reminder> reminder = reminderService.findById(id);
        if (!reminder.isPresent()) {
            return new ResponseEntity<>(
                new responseDTO(HttpStatus.NOT_FOUND.toString(), "Recordatorio no encontrado"), 
                HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reminder.get(), HttpStatus.OK);
    }

    // Update reminder
    @PutMapping("/{id}")
    public ResponseEntity<responseDTO> update(@PathVariable int id, @RequestBody reminderDTO reminderDTO) {
        responseDTO response = reminderService.update(id, reminderDTO);
        if (response.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Delete reminder
    @DeleteMapping("/{id}")
    public ResponseEntity<responseDTO> delete(@PathVariable int id) {
        responseDTO response = reminderService.delete(id);
        if (response.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}