package com.sena.schedule.DTO;

import java.time.LocalDateTime;

public class reminderLogDTO {
    private Integer id;
    private Integer reminderID;
    private String patientEmail;
    private String medicationName;
    private LocalDateTime sentAt;

    public reminderLogDTO() {}

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