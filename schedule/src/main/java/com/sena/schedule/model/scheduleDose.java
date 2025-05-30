package com.sena.schedule.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="scheduleDose")
public class scheduleDose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="doseID")
    private int doseID;

    @ManyToOne
    @JoinColumn(name="medicationID", nullable=false)
    private medicament medicationID;

    @ManyToOne
    @JoinColumn(name="patientID", nullable=false)
    private patient patientID;

    @Column(name="startDate", nullable=false, length=70)
    private Timestamp startDate;

    @Column(name = "isConfirmed", nullable = false, columnDefinition = "boolean default true ")
    private boolean isConfirmed;

    @Column(name="durationDays", nullable=false)
    private int durationDays;

    
    public scheduleDose() {
    }

    public scheduleDose(int doseID, medicament medicationID, patient patientID, Timestamp startDate, boolean isConfirmed, int durationDays) {
        this.doseID = doseID;
        this.medicationID = medicationID;
        this.patientID = patientID;
        this.startDate = startDate;
        this.isConfirmed = isConfirmed;
        this.durationDays = durationDays;
    }
    public int getDoseID() {
        return doseID;
    }
    public void setDoseID(int doseID) {
        this.doseID = doseID;
    }
    public medicament getMedicationID() {
        return medicationID;
    }
    public void setMedicationID(medicament medicationID) {
        this.medicationID = medicationID;
    }
    public patient getPatientID() {
        return patientID;
    }
    public void setPatientID(patient patientID) {
        this.patientID = patientID;
    }
    public Timestamp getStartDate() {
        return startDate;
    }
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }
    public int getDurationDays() {
        return durationDays;
    }
    public void setDurationDays(int durationDays) {
        this.durationDays = durationDays;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }
    
    

}
