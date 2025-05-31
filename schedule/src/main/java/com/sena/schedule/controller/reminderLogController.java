package com.sena.schedule.controller;

import com.sena.schedule.DTO.reminderLogDTO;
import com.sena.schedule.service.reminderLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminderlogs")
public class reminderLogController {

    @Autowired
    private reminderLogService service;

    @GetMapping
    public List<reminderLogDTO> findAll() {
        return service.findAll();
    }
}