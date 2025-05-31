package com.sena.schedule.repository;

import com.sena.schedule.model.scheduleDose;
import com.sena.schedule.model.patient;
import com.sena.schedule.model.medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IScheduleDose extends JpaRepository<scheduleDose, Integer> {
    List<scheduleDose> findByConfirmationStatus(Integer confirmationStatus);
    boolean existsByPatientAndMedicationAndStartDateAndConfirmationStatus(patient patient, medication medication, LocalDateTime startDate, Integer confirmationStatus);
}