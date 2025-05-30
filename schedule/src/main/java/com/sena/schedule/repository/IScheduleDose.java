package com.sena.schedule.repository;

import com.sena.schedule.model.scheduleDose;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IScheduleDose extends JpaRepository<scheduleDose, Integer> {
    boolean existsByPatientID_PatientID(int patientId);
    boolean existsByMedicationID_MedicationID(int medicationId);
}