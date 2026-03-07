package com.ai_failure_engine.service;

import com.ai_failure_engine.model.Alert;
import com.ai_failure_engine.model.AlertStatus;
import com.ai_failure_engine.repository.AlertRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

    private final AlertRepository alertRepository;

    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public Page<Alert> getAlerts(String serviceName, AlertStatus status, Pageable pageable) {

        if (serviceName != null && status != null) {
            return alertRepository.findByServiceNameAndStatus(serviceName, status, pageable);
        }

        if (serviceName != null) {
            return alertRepository.findByServiceName(serviceName, pageable);
        }

        if (status != null) {
            return alertRepository.findByStatus(status, pageable);
        }

        return alertRepository.findAll(pageable);
    }
}