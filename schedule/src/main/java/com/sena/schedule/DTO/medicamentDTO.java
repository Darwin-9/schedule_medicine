package com.sena.schedule.DTO;

public class medicamentDTO {

    private String name;
    private String dosage;
    private int frequencyHours;

    public medicamentDTO() {
    }

    public medicamentDTO(String name, String dosage, int frequencyHours) {
        this.name = name;
        this.dosage = dosage;
        this.frequencyHours = frequencyHours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
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
