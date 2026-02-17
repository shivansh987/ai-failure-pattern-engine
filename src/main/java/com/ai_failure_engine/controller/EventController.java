package com.ai_failure_engine.controller;

import com.ai_failure_engine.model.ErrorEvent;
import com.ai_failure_engine.service.ErrorEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.time.Instant;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;


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
    public ResponseEntity<Page<ErrorEvent>> getEvents(
            @RequestParam(required = false) Instant from,
            @RequestParam(required = false) Instant to,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable) {

        return ResponseEntity.ok(errorEventService.getEvents(from, to, pageable));
    }
}


