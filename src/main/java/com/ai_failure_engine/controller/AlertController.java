package com.ai_failure_engine.controller;

import com.ai_failure_engine.model.Alert;
import com.ai_failure_engine.model.AlertStatus;
import com.ai_failure_engine.service.AlertService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/alerts")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping
    public Page<Alert> getAlerts(
            @RequestParam(required = false) String serviceName,
            @RequestParam(required = false) AlertStatus status,
            Pageable pageable
    ) {
        return alertService.getAlerts(serviceName, status, pageable);
    }
}