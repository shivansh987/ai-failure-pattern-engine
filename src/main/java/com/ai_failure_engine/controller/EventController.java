package com.ai_failure_engine.controller;

import com.ai_failure_engine.model.ErrorEvent;
import com.ai_failure_engine.service.ErrorEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final ErrorEventService errorEventService;

    public EventController(ErrorEventService errorEventService) {
        this.errorEventService = errorEventService;
    }

    @PostMapping
    public ResponseEntity<String> receiveEvent(
            @Valid @RequestBody ErrorEvent errorEvent) {

        errorEventService.save(errorEvent);

        return new ResponseEntity<>(
                "Event saved successfully",
                HttpStatus.CREATED
        );
    }
    @GetMapping
    public ResponseEntity<List<ErrorEvent>> getAllEvents() {
        List<ErrorEvent> events = errorEventService.getAllEvents();
        return ResponseEntity.ok(events);
    }
}


