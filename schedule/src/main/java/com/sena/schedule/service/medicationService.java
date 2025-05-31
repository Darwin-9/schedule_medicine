package com.sena.schedule.service;

import com.sena.schedule.DTO.medicationDTO;
import com.sena.schedule.DTO.responseDTO;
import com.sena.schedule.model.medication;
import com.sena.schedule.repository.IMedication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class medicationService {

    @Autowired
    private IMedication medicationRepo;

    /**
     * Guarda un nuevo medicamento después de validar los datos básicos.
     */
    public responseDTO save(medicationDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank() || dto.getDosage() == null || dto.getDosage().isBlank() || dto.getFrequencyHours() <= 0) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "Datos del medicamento incompletos");
        }
        if (medicationRepo.existsByName(dto.getName())) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "Ya existe un medicamento con ese nombre");
        }
        medication med = convertToModel(dto);
        medicationRepo.save(med);
        return new responseDTO(HttpStatus.OK.toString(), "Medicamento creado correctamente");
    }

    /**
     * Retorna la lista de todos los medicamentos registrados como DTOs.
     */
    public List<medicationDTO> findAll() {
        return medicationRepo.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca un medicamento por su ID y lo retorna como DTO.
     */
    public Optional<medicationDTO> findById(int id) {
        return medicationRepo.findById(id).map(this::convertToDTO);
    }

    /**
     * Actualiza los datos de un medicamento existente.
     */
    public responseDTO update(int id, medicationDTO dto) {
        Optional<medication> opt = medicationRepo.findById(id);
        if (opt.isEmpty()) return new responseDTO(HttpStatus.NOT_FOUND.toString(), "Medicamento no encontrado");
        medication med = opt.get();
        med.setName(dto.getName());
        med.setDosage(dto.getDosage());
        med.setFrequencyHours(dto.getFrequencyHours());
        medicationRepo.save(med);
        return new responseDTO(HttpStatus.OK.toString(), "Medicamento actualizado correctamente");
    }

    /**
     * Elimina un medicamento por su ID.
     */
    public responseDTO delete(int id) {
        if (!medicationRepo.existsById(id)) return new responseDTO(HttpStatus.NOT_FOUND.toString(), "Medicamento no existe");
        medicationRepo.deleteById(id);
        return new responseDTO(HttpStatus.OK.toString(), "Medicamento eliminado correctamente");
    }

    /**
     * Convierte un medicationDTO a un modelo medication.
     */
    public medication convertToModel(medicationDTO dto) {
        medication m = new medication();
        m.setName(dto.getName());
        m.setDosage(dto.getDosage());
        m.setFrequencyHours(dto.getFrequencyHours());
        return m;
    }

    /**
     * Convierte un modelo medication a un DTO medicationDTO.
     */
    public medicationDTO convertToDTO(medication m) {
        medicationDTO dto = new medicationDTO();
        dto.setName(m.getName());
        dto.setDosage(m.getDosage());
        dto.setFrequencyHours(m.getFrequencyHours());
        return dto;
    }
}