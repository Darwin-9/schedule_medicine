package com.sena.schedule.repository;

import com.sena.schedule.model.medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedication extends JpaRepository<medication, Integer> {
    boolean existsByName(String name);
}