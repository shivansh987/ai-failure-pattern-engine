package com.ai_failure_engine.controller;

import com.ai_failure_engine.model.ErrorEvent;
import com.ai_failure_engine.service.ErrorEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.time.Instant;
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
    public ResponseEntity<List<ErrorEvent>> getEvents(
            @RequestParam(required = false) Instant from,
            @RequestParam(required = false) Instant to) {

        if (from != null && to != null) {
            return ResponseEntity.ok(errorEventService.getEventsByTimeRange(from, to));
        }

        return ResponseEntity.ok(errorEventService.getAllEvents());
    }
}


