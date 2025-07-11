package com.sena.schedule.repository;

import com.sena.schedule.model.patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatient extends JpaRepository<patient, Integer> {
    boolean existsByEmail(String email);
}