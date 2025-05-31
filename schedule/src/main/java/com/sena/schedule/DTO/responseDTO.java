package com.sena.schedule.DTO;

import com.sena.schedule.model.patient;

public class responseDTO {
    private String status;
    private String message;
    private String token;
    private patient patient;


    public responseDTO() {
    }

    public responseDTO(String status, String message, String token, patient patient) {
        this.status = status;
        this.message = message;
        this.token = token;
    }

    public responseDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public patient getPatient() {
        return patient;
    }

    public void setPatient(patient patient) {
        this.patient = patient;
    }

}