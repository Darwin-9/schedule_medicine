package com.sena.schedule.DTO;

public class reminderDTO {

    private int doseID;
    private String sendAt;
    private boolean status;

    public reminderDTO(int doseID, String sendAt, boolean status) {
        this.doseID = doseID;
        this.sendAt = sendAt;
        this.status = status;
    }

    public int getDoseID() {
        return doseID;
    }
    public void setDoseID(int doseID) {
        this.doseID = doseID;
    }
    public String getSendAt() {
        return sendAt;
    }
    public void setSendAt(String sendAt) {
        this.sendAt = sendAt;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}
