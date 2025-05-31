package com.sena.schedule.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reminder_log")
public class reminderLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "reminder_id", nullable = false)
    private Integer reminderID;

    @Column(name = "patient_email", nullable = false, length = 70)
    private String patientEmail;

    @Column(name = "medication_name", nullable = false, length = 50)
    private String medicationName;

    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt;

    public reminderLog() {
    }
    public reminderLog(Integer reminderID, String patientEmail, String medicationName, LocalDateTime sentAt) {
        this.reminderID = reminderID;
        this.patientEmail = patientEmail;
        this.medicationName = medicationName;
        this.sentAt = sentAt;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getReminderID() {
        return reminderID;
    }
    public void setReminderID(Integer reminderID) {
        this.reminderID = reminderID;
    }
    public String getPatientEmail() {
        return patientEmail;
    }
    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }
    public String getMedicationName() {
        return medicationName;
    }
    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }
    public LocalDateTime getSentAt() {
        return sentAt;
    }
    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    
}