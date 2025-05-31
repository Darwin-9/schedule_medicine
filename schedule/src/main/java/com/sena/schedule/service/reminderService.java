package com.sena.schedule.service;

import com.sena.schedule.DTO.reminderDTO;
import com.sena.schedule.DTO.responseDTO;
import com.sena.schedule.model.reminder;
import com.sena.schedule.model.scheduleDose;
import com.sena.schedule.repository.IReminder;
import com.sena.schedule.repository.IScheduleDose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class reminderService {

    @Autowired
    private IReminder reminderRepo;
    @Autowired
    private IScheduleDose scheduleDoseRepo;

    /**
     * Crea un nuevo recordatorio, validando la existencia de la dosis.
     */
    public responseDTO save(reminderDTO dto) {
        Optional<scheduleDose> sd = scheduleDoseRepo.findById(dto.getDoseID());
        if (sd.isEmpty()) return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "Dosis agendada no existe");
        reminder r = convertToModel(dto, sd.get());
        reminderRepo.save(r);
        return new responseDTO(HttpStatus.OK.toString(), "Recordatorio creado correctamente");
    }

    /**
     * Lista todos los recordatorios registrados como DTOs.
     */
    public List<reminderDTO> findAll() {
        return reminderRepo.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca un recordatorio por su ID y lo retorna como DTO.
     */
    public Optional<reminderDTO> findById(int id) {
        return reminderRepo.findById(id).map(this::convertToDTO);
    }

    /**
     * Actualiza un recordatorio existente.
     */
    public responseDTO update(int id, reminderDTO dto) {
        Optional<reminder> opt = reminderRepo.findById(id);
        if (opt.isEmpty()) return new responseDTO(HttpStatus.NOT_FOUND.toString(), "Recordatorio no encontrado");
        Optional<scheduleDose> sd = scheduleDoseRepo.findById(dto.getDoseID());
        if (sd.isEmpty()) return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "Dosis agendada no existe");
        reminder r = opt.get();
        r.setDoseID(sd.get());
        r.setSendAt(dto.getSendAt());
        r.setStatus(dto.getStatus());
        reminderRepo.save(r);
        return new responseDTO(HttpStatus.OK.toString(), "Recordatorio actualizado correctamente");
    }

    /**
     * Elimina un recordatorio por su ID.
     */
    public responseDTO delete(int id) {
        if (!reminderRepo.existsById(id)) return new responseDTO(HttpStatus.NOT_FOUND.toString(), "Recordatorio no existe");
        reminderRepo.deleteById(id);
        return new responseDTO(HttpStatus.OK.toString(), "Recordatorio eliminado correctamente");
    }

    /**
     * Convierte un reminderDTO a un modelo reminder.
     */
    public reminder convertToModel(reminderDTO dto, scheduleDose sd) {
        reminder r = new reminder();
        r.setDoseID(sd);
        r.setSendAt(dto.getSendAt());
        r.setStatus(dto.getStatus());
        return r;
    }

    /**
     * Convierte un modelo reminder a DTO reminderDTO.
     */
    public reminderDTO convertToDTO(reminder r) {
        reminderDTO dto = new reminderDTO();
        dto.setDoseID(r.getDoseID().getDoseID());
        dto.setSendAt(r.getSendAt());
        dto.setStatus(r.getStatus());
        return dto;
    }
}