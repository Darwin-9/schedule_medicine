package com.sena.schedule.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reminder")
public class reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reminderID", nullable = false)
    private Integer reminderID;

    @ManyToOne
    @JoinColumn(name = "doseID", nullable = false)
    private scheduleDose doseID;

    @Column(name = "sendAt", nullable = false)
    private LocalDateTime sendAt;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "active", nullable = false, columnDefinition = "boolean default true")
    private boolean active = true;

    public reminder() {
    }

    public reminder(Integer reminderID, scheduleDose doseID, LocalDateTime sendAt, Boolean status, boolean active) {
        this.reminderID = reminderID;
        this.doseID = doseID;
        this.sendAt = sendAt;
        this.status = status;
        this.active = active;
    }

    public Integer getReminderID() {
        return reminderID;
    }

    public void setReminderID(Integer reminderID) {
        this.reminderID = reminderID;
    }

    public scheduleDose getDoseID() {
        return doseID;
    }

    public void setDoseID(scheduleDose doseID) {
        this.doseID = doseID;
    }

    public LocalDateTime getSendAt() {
        return sendAt;
    }

    public void setSendAt(LocalDateTime sendAt) {
        this.sendAt = sendAt;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    
}