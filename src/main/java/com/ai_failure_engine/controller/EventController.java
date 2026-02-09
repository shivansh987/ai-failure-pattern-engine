package com.ai_failure_engine.controller;

import com.ai_failure_engine.model.ErrorEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    @PostMapping
    public ResponseEntity<String> receiveEvent(@Valid @RequestBody ErrorEvent errorEvent) {

        System.out.println("===== ERROR EVENT RECEIVED =====");
        System.out.println(errorEvent.getServiceName());
        System.out.println(errorEvent.getSeverity());
        System.out.println("================================");

        return new ResponseEntity<>("Event received successfully", HttpStatus.CREATED);
    }
}
