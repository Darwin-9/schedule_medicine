package com.sena.schedule.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.schedule.DTO.reminderDTO;
import com.sena.schedule.DTO.responseDTO;
import com.sena.schedule.model.reminder;
import com.sena.schedule.model.scheduleDose;
import com.sena.schedule.repository.IReminder;
import com.sena.schedule.repository.IScheduleDose;

@Service
public class reminderService {

    @Autowired
    private IReminder reminderRepository;

    @Autowired
    private IScheduleDose scheduleDoseRepository;

    // Create a new reminder
    public responseDTO save(reminderDTO reminderDTO) {
        try {
            // Validate dose exists
            Optional<scheduleDose> dose = scheduleDoseRepository.findById(reminderDTO.getDoseID());
            if (!dose.isPresent()) {
                return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La dosis especificada no existe");
            }

            // Validate and parse timestamp
            Timestamp sendAt = parseTimestamp(reminderDTO.getSendAt());
            if (sendAt == null) {
                return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "Formato de fecha/hora inválido. Use yyyy-MM-dd HH:mm:ss");
            }

            reminder newReminder = new reminder();
            newReminder.setDose(dose.get());
            newReminder.setSendAt(sendAt);
            newReminder.setStatus(reminderDTO.isStatus());

            reminderRepository.save(newReminder);

            return new responseDTO(HttpStatus.OK.toString(), "Recordatorio creado exitosamente");
        } catch (Exception e) {
            return new responseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error al crear el recordatorio: " + e.getMessage());
        }
    }

    // Get all reminders
    public List<reminder> findAll() {
        return reminderRepository.findAll();
    }

    // Get reminder by ID
    public Optional<reminder> findById(int id) {
        return reminderRepository.findById(id);
    }

    // Update reminder
    public responseDTO update(int id, reminderDTO reminderDTO) {
        try {
            Optional<reminder> reminderOpt = reminderRepository.findById(id);
            if (!reminderOpt.isPresent()) {
                return new responseDTO(HttpStatus.NOT_FOUND.toString(), "Recordatorio no encontrado");
            }

            // Validate dose exists if it's being updated
            if (reminderDTO.getDoseID() != 0) {
                Optional<scheduleDose> dose = scheduleDoseRepository.findById(reminderDTO.getDoseID());
                if (!dose.isPresent()) {
                    return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La dosis especificada no existe");
                }
                reminderOpt.get().setDose(dose.get());
            }

            // Validate and parse timestamp if it's being updated
            if (reminderDTO.getSendAt() != null && !reminderDTO.getSendAt().isEmpty()) {
                Timestamp sendAt = parseTimestamp(reminderDTO.getSendAt());
                if (sendAt == null) {
                    return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "Formato de fecha/hora inválido. Use yyyy-MM-dd HH:mm:ss");
                }
                reminderOpt.get().setSendAt(sendAt);
            }

            // Update status
            reminderOpt.get().setStatus(reminderDTO.isStatus());

            reminderRepository.save(reminderOpt.get());
            return new responseDTO(HttpStatus.OK.toString(), "Recordatorio actualizado correctamente");
        } catch (Exception e) {
            return new responseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error al actualizar el recordatorio: " + e.getMessage());
        }
    }

    // Delete reminder
    public responseDTO delete(int id) {
        Optional<reminder> reminder = reminderRepository.findById(id);
        if (!reminder.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND.toString(), "Recordatorio no encontrado");
        }

        reminderRepository.deleteById(id);
        return new responseDTO(HttpStatus.OK.toString(), "Recordatorio eliminado correctamente");
    }

    // Helper method to parse timestamp
    private Timestamp parseTimestamp(String timestampStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return new Timestamp(dateFormat.parse(timestampStr).getTime());
        } catch (ParseException e) {
            return null;
        }
    }
}