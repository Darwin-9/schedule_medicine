package com.sena.schedule.DTO;

public class responseDTO {
    
    private String status;
    private String message;


    public responseDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
