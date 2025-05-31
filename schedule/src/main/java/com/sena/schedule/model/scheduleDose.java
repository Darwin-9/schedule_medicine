package com.sena.schedule.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "scheduleDose")
public class scheduleDose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doseID", nullable = false)
    private Integer doseID;

    @ManyToOne
    @JoinColumn(name = "medicationID", nullable = false)
    private medication medication;

    @ManyToOne
    @JoinColumn(name = "patientID", nullable = false)
    private patient patient;

    @Column(name = "startDate", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "confirmationStatus", nullable = false)
    private Integer confirmationStatus; // 0=pending, 1=confirmed, 2=not taken

    @Column(name = "durationDays", nullable = false)
    private Integer durationDays;

    public scheduleDose() {
    }

    public scheduleDose(Integer doseID, medication medication, patient patient, LocalDateTime startDate, Integer confirmationStatus, Integer durationDays) {
        this.doseID = doseID;
        this.medication = medication;
        this.patient = patient;
        this.startDate = startDate;
        this.confirmationStatus = confirmationStatus;
        this.durationDays = durationDays;
    }

    public Integer getDoseID() {
        return doseID;
    }

    public void setDoseID(Integer doseID) {
        this.doseID = doseID;
    }

    public medication getMedication() {
        return medication;
    }

    public void setMedication(medication medication) {
        this.medication = medication;
    }

    public patient getPatient() {
        return patient;
    }

    public void setPatient(patient patient) {
        this.patient = patient;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getConfirmationStatus() {
        return confirmationStatus;
    }

    public void setConfirmationStatus(Integer confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }

    public Integer getDurationDays() {
        return durationDays;
    }

    public void setDurationDays(Integer durationDays) {
        this.durationDays = durationDays;
    }

    
}