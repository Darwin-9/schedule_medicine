package com.sena.schedule.repository;

import com.sena.schedule.model.reminderLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReminderLog extends JpaRepository<reminderLog, Integer> {
}