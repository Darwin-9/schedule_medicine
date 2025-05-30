package com.sena.schedule.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.schedule.DTO.responseDTO;
import com.sena.schedule.DTO.scheduleDoseDTO;
import com.sena.schedule.model.medicament;
import com.sena.schedule.model.patient;
import com.sena.schedule.model.scheduleDose;
import com.sena.schedule.repository.IMedicament;
import com.sena.schedule.repository.IPatient;
import com.sena.schedule.repository.IScheduleDose;

@Service
public class scheduleDoseService {

    @Autowired
    private IScheduleDose scheduleDoseRepository;

    @Autowired
    private IPatient patientRepository;

    @Autowired
    private IMedicament medicamentRepository;

    public responseDTO save(scheduleDoseDTO scheduleDoseDTO) {
        try {
            Optional<patient> patient = patientRepository.findById(scheduleDoseDTO.getPatientID());
            if (!patient.isPresent()) {
                return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El paciente especificado no existe");
            }

            Optional<medicament> medication = medicamentRepository.findById(scheduleDoseDTO.getMedicationID());
            if (!medication.isPresent()) {
                return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El medicamento especificado no existe");
            }

            // Validate and parse timestamp
            Timestamp startDate = parseTimestamp(scheduleDoseDTO.getStartDate());
            if (startDate == null) {
                return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "Formato de fecha inválido. Use yyyy-MM-dd HH:mm:ss");
            }

            if (scheduleDoseDTO.getDurationDays() <= 0) {
                return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La duración en días debe ser mayor a 0");
            }

            scheduleDose newDose = new scheduleDose();
            newDose.setPatientID(patient.get());
            newDose.setMedicationID(medication.get());
            newDose.setStartDate(startDate);
            newDose.setConfirmed(scheduleDoseDTO.isConfirmed());
            newDose.setDurationDays(scheduleDoseDTO.getDurationDays());

            scheduleDoseRepository.save(newDose);

            return new responseDTO(HttpStatus.OK.toString(), "Dosis programada creada exitosamente");
        } catch (Exception e) {
            return new responseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error al crear la dosis programada: " + e.getMessage());
        }
    }

    public List<scheduleDose> findAll() {
        return scheduleDoseRepository.findAll();
    }

    public Optional<scheduleDose> findById(int id) {
        return scheduleDoseRepository.findById(id);
    }

    public responseDTO update(int id, scheduleDoseDTO scheduleDoseDTO) {
        try {
            Optional<scheduleDose> doseOpt = scheduleDoseRepository.findById(id);
            if (!doseOpt.isPresent()) {
                return new responseDTO(HttpStatus.NOT_FOUND.toString(), "Dosis programada no encontrada");
            }

            scheduleDose existingDose = doseOpt.get();

            if (scheduleDoseDTO.getPatientID() != 0 && 
                existingDose.getPatientID().getPatientID() != scheduleDoseDTO.getPatientID()) {
                Optional<patient> patient = patientRepository.findById(scheduleDoseDTO.getPatientID());
                if (!patient.isPresent()) {
                    return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El paciente especificado no existe");
                }
                existingDose.setPatientID(patient.get());
            }

            if (scheduleDoseDTO.getMedicationID() != 0 && 
                existingDose.getMedicationID().getMedicamentID() != scheduleDoseDTO.getMedicationID()) {
                Optional<medicament> medication = medicamentRepository.findById(scheduleDoseDTO.getMedicationID());
                if (!medication.isPresent()) {
                    return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El medicamento especificado no existe");
                }
                existingDose.setMedicationID(medication.get());
            }

            // Update start date if changed
            if (scheduleDoseDTO.getStartDate() != null && !scheduleDoseDTO.getStartDate().isEmpty()) {
                Timestamp startDate = parseTimestamp(scheduleDoseDTO.getStartDate());
                if (startDate == null) {
                    return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "Formato de fecha inválido. Use yyyy-MM-dd HH:mm:ss");
                }
                existingDose.setStartDate(startDate);
            }

            existingDose.setConfirmed(scheduleDoseDTO.isConfirmed());

            if (scheduleDoseDTO.getDurationDays() > 0) {
                existingDose.setDurationDays(scheduleDoseDTO.getDurationDays());
            }

            scheduleDoseRepository.save(existingDose);
            return new responseDTO(HttpStatus.OK.toString(), "Dosis programada actualizada correctamente");
        } catch (Exception e) {
            return new responseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error al actualizar la dosis programada: " + e.getMessage());
        }
    }

    public responseDTO delete(int id) {
        Optional<scheduleDose> dose = scheduleDoseRepository.findById(id);
        if (!dose.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND.toString(), "Dosis programada no encontrada");
        }

        scheduleDoseRepository.deleteById(id);
        return new responseDTO(HttpStatus.OK.toString(), "Dosis programada eliminada correctamente");
    }

    private Timestamp parseTimestamp(String timestampStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return new Timestamp(dateFormat.parse(timestampStr).getTime());
        } catch (ParseException e) {
            return null;
        }
    }
}