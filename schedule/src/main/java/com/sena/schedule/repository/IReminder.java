package com.sena.schedule.repository;

import com.sena.schedule.model.reminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReminder extends JpaRepository<reminder, Integer> {

}
