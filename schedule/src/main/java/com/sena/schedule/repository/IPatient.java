package com.sena.schedule.repository;

import com.sena.schedule.DTO.patientDTO;
import com.sena.schedule.DTO.responseDTO;
import com.sena.schedule.model.patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatient extends JpaRepository<patient, Integer> {
    boolean existsByEmail(String email);
    responseDTO save(patientDTO patientdto);

}
