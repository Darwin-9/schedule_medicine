package com.sena.schedule.service;

import com.sena.schedule.DTO.reminderLogDTO;
import com.sena.schedule.model.reminderLog;
import com.sena.schedule.repository.IReminderLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class reminderLogService {

    @Autowired
    private IReminderLog reminderLogRepo;

    public List<reminderLogDTO> findAll() {
        return reminderLogRepo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public reminderLogDTO toDTO(reminderLog log) {
        reminderLogDTO dto = new reminderLogDTO();
        dto.setId(log.getId());
        dto.setReminderID(log.getReminderID());
        dto.setPatientEmail(log.getPatientEmail());
        dto.setMedicationName(log.getMedicationName());
        dto.setSentAt(log.getSentAt());
        return dto;
    }

    public reminderLog toModel(reminderLogDTO dto) {
        reminderLog log = new reminderLog();
        log.setId(dto.getId());
        log.setReminderID(dto.getReminderID());
        log.setPatientEmail(dto.getPatientEmail());
        log.setMedicationName(dto.getMedicationName());
        log.setSentAt(dto.getSentAt());
        return log;
    }
}