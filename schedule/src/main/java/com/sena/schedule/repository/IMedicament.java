package com.sena.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.schedule.model.medicament;

public interface IMedicament extends JpaRepository<medicament, Integer> {

    boolean existsByName(String name);

}
