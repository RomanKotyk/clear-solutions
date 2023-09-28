package com.example.userapp.advice;

import java.io.Serializable;
import java.time.LocalDate;

public class ErrorDetails implements Serializable {
    private LocalDate timestamp;
    private String message;
    private Integer code;
    public ErrorDetails(LocalDate timestamp, String message, Integer code) {
        this.timestamp = timestamp;
        this.message = message;
        this.code = code;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
