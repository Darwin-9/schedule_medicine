package com.sena.schedule.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.schedule.DTO.patientDTO;
import com.sena.schedule.DTO.responseDTO;
import com.sena.schedule.model.patient;
import com.sena.schedule.repository.IPatient;

@Service
public class patientService {

    @Autowired
    private IPatient data;

    // Método para guardar un paciente con validaciones
    public responseDTO save(patientDTO patientDTO) {
        if (patientDTO.getName() == null || patientDTO.getName().trim().isEmpty()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El nombre del paciente no puede estar vacío");
        }

        if (patientDTO.getName().length() < 3 || patientDTO.getName().length() > 50) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El nombre debe tener entre 3 y 50 caracteres");
        }

        if (!isValidEmail(patientDTO.getEmail())) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El correo electrónico no tiene un formato válido");
        }

        if (data.existsByEmail(patientDTO.getEmail())) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El correo electrónico ya está registrado");
        }

        patient newPatient = convertToModel(patientDTO);
        data.save(newPatient);

        return new responseDTO(HttpStatus.OK.toString(), "Paciente registrado exitosamente");
    }

    // Método para obtener todos los pacientes
    public List<patient> findAll() {
        return data.findAll();
    }

    // Método para buscar un paciente por ID
    public Optional<patient> findById(int id) {
        return data.findById(id);
    }

    // Método para actualizar un paciente
    public responseDTO update(int id, patientDTO patientDTO) {
        Optional<patient> patientOpt = data.findById(id);
        if (!patientOpt.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND.toString(), "El paciente con ID " + id + " no existe");
        }

        if (patientDTO.getName() == null || patientDTO.getName().trim().isEmpty()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El nombre del paciente no puede estar vacío");
        }

        if (patientDTO.getName().length() < 3 || patientDTO.getName().length() > 50) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El nombre debe tener entre 3 y 50 caracteres");
        }

        if (!isValidEmail(patientDTO.getEmail())) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El correo electrónico no tiene un formato válido");
        }

        // Verificar si el email ya existe (excepto para el paciente actual)
        patient existingPatient = patientOpt.get();
        if (!existingPatient.getEmail().equals(patientDTO.getEmail()) && 
            data.existsByEmail(patientDTO.getEmail())) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El correo electrónico ya está registrado");
        }

        existingPatient.setName(patientDTO.getName());
        existingPatient.setEmail(patientDTO.getEmail());
        existingPatient.setNotificationPermission(patientDTO.isNotificationPermission());

        data.save(existingPatient);
        return new responseDTO(HttpStatus.OK.toString(), "Paciente actualizado correctamente");
    }

    // Método para eliminar un paciente
    public responseDTO delete(int id) {
        Optional<patient> patientOpt = data.findById(id);
        if (!patientOpt.isPresent()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El paciente no existe");
        }

        data.deleteById(id);
        return new responseDTO(HttpStatus.OK.toString(), "Paciente eliminado correctamente");
    }

    // Método para convertir DTO a Model
    private patient convertToModel(patientDTO patientDTO) {
        patient patient = new patient();
        patient.setName(patientDTO.getName());
        patient.setEmail(patientDTO.getEmail());
        patient.setNotificationPermission(patientDTO.isNotificationPermission());
        return patient;
    }

    // Método para validar email
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && email.matches(emailRegex);
    }
}