package com.ai_failure_engine.exception;

import java.util.Map;

public class ErrorResponse {

    private int status;
    private String error;
    private Map<String, String> details;

    public ErrorResponse(int status, String error, Map<String, String> details) {
        this.status = status;
        this.error = error;
        this.details = details;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public Map<String, String> getDetails() {
        return details;
    }
}
