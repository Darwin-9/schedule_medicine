package com.sena.schedule.service;

import com.sena.schedule.DTO.patientDTO;
import com.sena.schedule.DTO.responseDTO;
import com.sena.schedule.model.patient;
import com.sena.schedule.repository.IPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class patientService {

    @Autowired
    private IPatient patientRepo;

    /**
     * Guarda un nuevo paciente después de validar los datos básicos.
     */
    public responseDTO save(patientDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank() || dto.getEmail() == null || dto.getEmail().isBlank()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "Datos del paciente incompletos");
        }
        if (patientRepo.existsByEmail(dto.getEmail())) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El correo ya está registrado");
        }
        patient p = convertToModel(dto);
        patientRepo.save(p);
        return new responseDTO(HttpStatus.OK.toString(), "Paciente creado correctamente");
    }

    /**
     * Retorna la lista de todos los pacientes registrados como DTOs.
     */
    public List<patientDTO> findAll() {
        return patientRepo.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca un paciente por su ID y lo retorna como DTO.
     */
    public Optional<patientDTO> findById(int id) {
        return patientRepo.findById(id).map(this::convertToDTO);
    }

    /**
     * Actualiza los datos de un paciente existente.
     */
    public responseDTO update(int id, patientDTO dto) {
        Optional<patient> opt = patientRepo.findById(id);
        if (opt.isEmpty()) return new responseDTO(HttpStatus.NOT_FOUND.toString(), "Paciente no encontrado");
        patient p = opt.get();
        p.setName(dto.getName());
        p.setEmail(dto.getEmail());
        p.setNotificationPermission(dto.isNotificationPermission());
        patientRepo.save(p);
        return new responseDTO(HttpStatus.OK.toString(), "Paciente actualizado correctamente");
    }

    /**
     * Elimina un paciente por su ID.
     */
    public responseDTO delete(int id) {
        if (!patientRepo.existsById(id)) return new responseDTO(HttpStatus.NOT_FOUND.toString(), "Paciente no existe");
        patientRepo.deleteById(id);
        return new responseDTO(HttpStatus.OK.toString(), "Paciente eliminado correctamente");
    }

    /**
     * Convierte un patientDTO a un modelo patient.
     */
    public patient convertToModel(patientDTO dto) {
        patient p = new patient();
        p.setName(dto.getName());
        p.setEmail(dto.getEmail());
        p.setNotificationPermission(dto.isNotificationPermission());
        return p;
    }

    /**
     * Convierte un modelo patient a un DTO patientDTO.
     */
    public patientDTO convertToDTO(patient p) {
        patientDTO dto = new patientDTO();
        dto.setName(p.getName());
        dto.setEmail(p.getEmail());
        dto.setNotificationPermission(p.isNotificationPermission());
        return dto;
    }
}