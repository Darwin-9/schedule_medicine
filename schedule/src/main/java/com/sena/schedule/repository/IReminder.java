package com.sena.schedule.repository;

import com.sena.schedule.model.reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IReminder extends JpaRepository<reminder, Integer> {
    List<reminder> findByDoseID_DoseID(Integer doseID);
    Optional<reminder> findFirstByDoseID_DoseIDOrderBySendAtAsc(Integer doseID);
}