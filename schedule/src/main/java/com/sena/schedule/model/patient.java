package com.sena.schedule.model;

import jakarta.persistence.*;

@Entity
@Table(name = "patient")
public class patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="patientID")
    private int patientID;

    @Column(name="name", nullable=false, length=50)
    private String name;

    @Column(name="email", nullable=false, length=70, unique=true)
    private String email;

    @Column(name = "notificationPermission", nullable = false, columnDefinition = "boolean default false")
    private boolean notificationPermission;

    public patient() {
    }

    public patient(int patientID, String name, String email, boolean notificationPermission) {
        this.patientID = patientID;
        this.name = name;
        this.email = email;
        this.notificationPermission = notificationPermission;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isNotificationPermission() {
        return notificationPermission;
    }

    public void setNotificationPermission(boolean notificationPermission) {
        this.notificationPermission = notificationPermission;
    }

    
}