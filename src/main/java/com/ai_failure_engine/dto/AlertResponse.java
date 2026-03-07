package com.ai_failure_engine.dto;

import com.ai_failure_engine.model.ErrorEvent;

public class AlertResponse {

    private ErrorEvent event;
    private boolean spikeDetected;
    private String message;

    public AlertResponse(ErrorEvent event,
                         boolean spikeDetected,
                         String message) {
        this.event = event;
        this.spikeDetected = spikeDetected;
        this.message = message;
    }

    public ErrorEvent getEvent() {
        return event;
    }

    public boolean isSpikeDetected() {
        return spikeDetected;
    }

    public String getMessage() {
        return message;
    }
}
