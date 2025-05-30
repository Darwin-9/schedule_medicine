package com.sena.schedule.DTO;

public class scheduleDoseDTO {

    private int medicationID;
    private int patientID;
    private String startDate;
    private boolean isConfirmed;
    private int durationDays;
   
    public scheduleDoseDTO(int medicationID, int patientID, String startDate, boolean isConfirmed, int durationDays) {
        this.medicationID = medicationID;
        this.patientID = patientID;
        this.startDate = startDate;
        this.isConfirmed = isConfirmed;
        this.durationDays = durationDays;
    }

    public int getMedicationID() {
        return medicationID;
    }
    public void setMedicationID(int medicationID) {
        this.medicationID = medicationID;
    }

    public int getPatientID() {
        return patientID;
    }
    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }
    public void setConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public int getDurationDays() {
        return durationDays;
    }
    public void setDurationDays(int durationDays) {
        this.durationDays = durationDays;
    }

    

}
