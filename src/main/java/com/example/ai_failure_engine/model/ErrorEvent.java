package com.example.ai_failure_engine.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ErrorEvent {
    @NotBlank(message = "serviceName is required")
    private String serviceName;

    @NotBlank(message = "errorCode is required")
    private String errorCode;

    @NotBlank(message = "message is required")
    private String message;

    @NotBlank(message = "severity is required")
    private String severity;

    @NotNull(message = "timestamp is required")
    private LocalDateTime timestamp;

    public ErrorEvent() {
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
