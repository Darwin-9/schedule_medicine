package com.sena.schedule.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.schedule.DTO.medicamentDTO;
import com.sena.schedule.DTO.responseDTO;
import com.sena.schedule.model.medicament;
import com.sena.schedule.repository.IMedicament;

@Service
public class medicamentService {

    @Autowired
    private IMedicament data;

    
    public responseDTO save(medicamentDTO medicamentDTO) {
        if (medicamentDTO.getName() == null || medicamentDTO.getName().length() < 3 || medicamentDTO.getName().length() > 50) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El nombre del medicamento debe tener entre 3 y 50 caracteres");
        }

        if (medicamentDTO.getDosage() == null || medicamentDTO.getDosage().isEmpty()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La dosis no puede estar vacía");
        }

        if (medicamentDTO.getFrequencyHours() <= 0) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La frecuencia en horas debe ser mayor a 0");
        }

       
        if (data.existsByName(medicamentDTO.getName())) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "Ya existe un medicamento con este nombre");
        }

        medicament medicament = convertToModel(medicamentDTO);
        data.save(medicament);

        return new responseDTO(HttpStatus.OK.toString(), "Medicamento guardado exitosamente");
    }

   
    public List<medicament> findAll() {
        return data.findAll();
    }

    
    public Optional<medicament> findById(int id) {
        return data.findById(id);
    }

    public responseDTO delete(int id) {
        Optional<medicament> medicament = findById(id);
        if (!medicament.isPresent()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El medicamento no existe");
        }
    
        data.delete(medicament.get());
    
        return new responseDTO(HttpStatus.OK.toString(), "Medicamento eliminado correctamente");
    }

    
    public responseDTO update(int id, medicamentDTO dto) {
        Optional<medicament> medicamentOpt = data.findById(id);
        if (!medicamentOpt.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND.toString(), "El medicamento con ID " + id + " no existe");
        }
    
        if (dto.getName() == null || dto.getName().length() < 3 || dto.getName().length() > 50) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El nombre del medicamento debe tener entre 3 y 50 caracteres");
        }
    
        if (dto.getDosage() == null || dto.getDosage().isEmpty()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La dosis no puede estar vacía");
        }
    
        if (dto.getFrequencyHours() <= 0) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La frecuencia en horas debe ser mayor a 0");
        }
    
       
    
        medicament existingMedicament = medicamentOpt.get();
        existingMedicament.setName(dto.getName());
        existingMedicament.setDosage(dto.getDosage());
        existingMedicament.setFrequencyHours(dto.getFrequencyHours());
    
        data.save(existingMedicament);
    
        return new responseDTO(HttpStatus.OK.toString(), "Medicamento actualizado correctamente");
    }

    
    public medicament convertToModel(medicamentDTO medicamentDTO) {
        return new medicament(
            0, 
            medicamentDTO.getName(),
            medicamentDTO.getDosage(),
            medicamentDTO.getFrequencyHours()
        );
    }

   
    public medicamentDTO convertToDTO(medicament medicament) {
        return new medicamentDTO(
            medicament.getName(),
            medicament.getDosage(),
            medicament.getFrequencyHours()
        );
    }
}