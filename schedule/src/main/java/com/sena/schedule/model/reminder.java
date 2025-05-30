package com.sena.schedule.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="reminder")
public class reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reminderID")
    private int patientID;
 
    @ManyToOne
    @JoinColumn(name="doseID", nullable=false)
    private scheduleDose dose;

    @Column(name="sendAt", nullable=false, length=70, unique=true)
    private Timestamp sendAt;

    @Column(name = "status", nullable = false, columnDefinition = "boolean default true ")
    private boolean status;

    public reminder() {
    }

    public reminder(int patientID, scheduleDose dose, Timestamp sendAt, boolean status) {
        this.patientID = patientID;
        this.dose = dose;
        this.sendAt = sendAt;
        this.status = status;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public scheduleDose getDose() {
        return dose;
    }

    public void setDose(scheduleDose dose) {
        this.dose = dose;
    }

    public Timestamp getSendAt() {
        return sendAt;
    }

    public void setSendAt(Timestamp sendAt) {
        this.sendAt = sendAt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    

}
