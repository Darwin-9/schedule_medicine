package com.sena.schedule.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.sena.schedule.model.scheduleDose;
import com.sena.schedule.DTO.scheduleDoseDTO;
import com.sena.schedule.DTO.responseDTO;
import com.sena.schedule.service.scheduleDoseService;

@RestController
@RequestMapping("/api/v1/schedule-doses")
public class scheduleDoseController {

    @Autowired
    private scheduleDoseService scheduleDoseService;

    // Create a new schedule dose
    @PostMapping("/")
    public ResponseEntity<responseDTO> create(@RequestBody scheduleDoseDTO scheduleDoseDTO) {
        responseDTO response = scheduleDoseService.save(scheduleDoseDTO);
        if (response.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    // Get all schedule doses
    @GetMapping("/")
    public ResponseEntity<List<scheduleDose>> getAll() {
        return new ResponseEntity<>(scheduleDoseService.findAll(), HttpStatus.OK);
    }

    // Get schedule dose by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        Optional<scheduleDose> dose = scheduleDoseService.findById(id);
        if (!dose.isPresent()) {
            return new ResponseEntity<>(
                new responseDTO(HttpStatus.NOT_FOUND.toString(), "Dosis programada no encontrada"), 
                HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dose.get(), HttpStatus.OK);
    }

    // Update schedule dose
    @PutMapping("/{id}")
    public ResponseEntity<responseDTO> update(@PathVariable int id, @RequestBody scheduleDoseDTO scheduleDoseDTO) {
        responseDTO response = scheduleDoseService.update(id, scheduleDoseDTO);
        if (response.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Delete schedule dose
    @DeleteMapping("/{id}")
    public ResponseEntity<responseDTO> delete(@PathVariable int id) {
        responseDTO response = scheduleDoseService.delete(id);
        if (response.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}