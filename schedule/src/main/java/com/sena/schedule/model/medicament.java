package com.sena.schedule.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="medicament")
public class medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="medicamentID")
    private int medicamentID;

    @Column(name="name", nullable=false, length=50)
    private String name;

    @Column(name="dosage", nullable=false, length=10)
    private String dosage;

    @Column(name="frequencyHours", nullable=false)
    private int frequencyHours;

    public medicament(){

    }

    public medicament(int medicamentID, String name, String dosage, int frequencyHours){
        this.medicamentID = medicamentID;
        this.name = name;
        this.dosage = dosage;
        this.frequencyHours = frequencyHours;

    }

    public int getMedicamentID() {
        return medicamentID;
    }

    public String getName() {
        return name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setMedicamentID(int medicamentID) {
        this.medicamentID = medicamentID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
    
    public int getFrequencyHours() {
        return frequencyHours;
    }
    public void setFrequencyHours(int frequencyHours) {
        this.frequencyHours = frequencyHours;
    }

    

}
