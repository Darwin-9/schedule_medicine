package com.sena.schedule.service;

import com.sena.schedule.DTO.scheduleDoseDTO;
import com.sena.schedule.DTO.responseDTO;
import com.sena.schedule.model.scheduleDose;
import com.sena.schedule.model.patient;
import com.sena.schedule.model.medication;
import com.sena.schedule.repository.IScheduleDose;
import com.sena.schedule.repository.IPatient;
import com.sena.schedule.repository.IMedication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class scheduleDoseService {

    @Autowired
    private IScheduleDose scheduleDoseRepo;
    @Autowired
    private IPatient patientRepo;
    @Autowired
    private IMedication medicationRepo;

    /**
     * Guarda una nueva dosis agendada luego de validar datos y existencia de paciente y medicamento.
     */
    public responseDTO save(scheduleDoseDTO dto) {
        Optional<patient> p = patientRepo.findById(dto.getPatientID());
        Optional<medication> m = medicationRepo.findById(dto.getMedicationID());
        if (p.isEmpty() || m.isEmpty()) return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "Paciente o medicamento no existe");
        if (dto.getStartDate() == null || dto.getDurationDays() == null || dto.getDurationDays() <= 0)
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "Datos de la dosis incompletos");
        scheduleDose sd = convertToModel(dto, m.get(), p.get());
        scheduleDoseRepo.save(sd);
        return new responseDTO(HttpStatus.OK.toString(), "Dosis agendada correctamente");
    }

    /**
     * Retorna todas las dosis agendadas como DTOs.
     */
    public List<scheduleDoseDTO> findAll() {
        return scheduleDoseRepo.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca una dosis agendada por su ID y la retorna como DTO.
     */
    public Optional<scheduleDoseDTO> findById(int id) {
        return scheduleDoseRepo.findById(id).map(this::convertToDTO);
    }

    /**
     * Actualiza una dosis agendada.
     */
    public responseDTO update(int id, scheduleDoseDTO dto) {
        Optional<scheduleDose> opt = scheduleDoseRepo.findById(id);
        if (opt.isEmpty()) return new responseDTO(HttpStatus.NOT_FOUND.toString(), "Dosis no encontrada");
        Optional<patient> p = patientRepo.findById(dto.getPatientID());
        Optional<medication> m = medicationRepo.findById(dto.getMedicationID());
        if (p.isEmpty() || m.isEmpty()) return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "Paciente o medicamento no existe");
        scheduleDose sd = opt.get();
        sd.setMedication(m.get());
        sd.setPatient(p.get());
        sd.setStartDate(dto.getStartDate());
        sd.setConfirmationStatus(dto.getConfirmationStatus());
        sd.setDurationDays(dto.getDurationDays());
        scheduleDoseRepo.save(sd);
        return new responseDTO(HttpStatus.OK.toString(), "Dosis agendada actualizada correctamente");
    }

    /**
     * Elimina una dosis agendada por su ID.
     */
    public responseDTO delete(int id) {
        if (!scheduleDoseRepo.existsById(id)) return new responseDTO(HttpStatus.NOT_FOUND.toString(), "Dosis no existe");
        scheduleDoseRepo.deleteById(id);
        return new responseDTO(HttpStatus.OK.toString(), "Dosis agendada eliminada correctamente");
    }

    /**
     * Convierte un scheduleDoseDTO a un modelo scheduleDose.
     */
    public scheduleDose convertToModel(scheduleDoseDTO dto, medication med, patient pat) {
        scheduleDose sd = new scheduleDose();
        sd.setMedication(med);
        sd.setPatient(pat);
        sd.setStartDate(dto.getStartDate());
        sd.setConfirmationStatus(dto.getConfirmationStatus());
        sd.setDurationDays(dto.getDurationDays());
        return sd;
    }

    /**
     * Convierte un modelo scheduleDose a un DTO scheduleDoseDTO.
     */
    public scheduleDoseDTO convertToDTO(scheduleDose sd) {
        scheduleDoseDTO dto = new scheduleDoseDTO();
        dto.setMedicationID(sd.getMedication().getMedicationID());
        dto.setPatientID(sd.getPatient().getPatientID());
        dto.setStartDate(sd.getStartDate());
        dto.setConfirmationStatus(sd.getConfirmationStatus());
        dto.setDurationDays(sd.getDurationDays());
        return dto;
    }
}