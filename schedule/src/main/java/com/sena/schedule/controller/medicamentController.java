package com.sena.schedule.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.sena.schedule.model.medicament;
import com.sena.schedule.DTO.medicamentDTO;
import com.sena.schedule.DTO.responseDTO;
import com.sena.schedule.service.medicamentService;

@RestController
@RequestMapping("/api/v1/medicaments")
public class medicamentController {

    @Autowired
    private medicamentService medicamentService;

    @PostMapping("/")
    public ResponseEntity<Object> register(@RequestBody medicamentDTO medicamentDTO) {
        responseDTO respuesta = medicamentService.save(medicamentDTO);
        if (respuesta.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<medicament>> getAll() {
        return new ResponseEntity<>(medicamentService.findAll(), HttpStatus.OK); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        responseDTO response = medicamentService.delete(id);
        if (response.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody medicamentDTO dto) {
        responseDTO response = medicamentService.update(id, dto);
        if (response.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
