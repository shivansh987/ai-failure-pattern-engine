package com.ai_failure_engine.service;

import com.ai_failure_engine.model.ErrorEvent;
import com.ai_failure_engine.repository.ErrorEventRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ai_failure_engine.dto.AlertResponse;
import com.ai_failure_engine.repository.AlertRepository;
import com.ai_failure_engine.model.Alert;
import com.ai_failure_engine.model.AlertStatus;
import java.util.Optional;
@Service
public class ErrorEventService {
    @Value("${alert.spike.threshold}")
    private int threshold;
    private final ErrorEventRepository errorEventRepository;
    private final PatternDetectionService patternDetectionService;
    private final AlertRepository alertRepository;

    // ✅ Constructor Injection (IMPORTANT)
    public ErrorEventService(ErrorEventRepository errorEventRepository,
                             PatternDetectionService patternDetectionService,
                             AlertRepository alertRepository) {
        this.errorEventRepository = errorEventRepository;
        this.patternDetectionService = patternDetectionService;
        this.alertRepository = alertRepository;
    }

    @Transactional
    public AlertResponse save(ErrorEvent errorEvent) {

        ErrorEvent saved = errorEventRepository.save(errorEvent);

        long count = patternDetectionService
                .countRecentHighSeverity(saved.getServiceName());

        boolean spikeDetected = false;
        String message = null;

        Optional<Alert> activeAlert =
                alertRepository.findByServiceNameAndStatus(
                        saved.getServiceName(),
                        AlertStatus.ACTIVE
                );

        // CASE 1: Threshold crossed → create alert
        if (count == threshold + 1 && activeAlert.isEmpty()) {

            Alert alert = new Alert();
            alert.setServiceName(saved.getServiceName());
            alert.setThreshold(threshold);
            alert.setStatus(AlertStatus.ACTIVE);
            alert.setTriggeredAt(Instant.now());

            alertRepository.save(alert);

            spikeDetected = true;
            message = "Spike detected: " + count +
                    " HIGH/CRITICAL errors in last window";
        }

        // CASE 2: Errors dropped → resolve alert
        if (count < threshold && activeAlert.isPresent()) {

            Alert alert = activeAlert.get();
            alert.setStatus(AlertStatus.RESOLVED);
            alert.setResolvedAt(Instant.now());

            alertRepository.save(alert);
        }

        return new AlertResponse(saved, spikeDetected, message);
    }


    public List<ErrorEvent> getAllEvents() {
        return errorEventRepository.findAll();
    }

    public Page<ErrorEvent> getEvents(Instant from, Instant to, Pageable pageable) {

        if (from != null && to != null) {
            return errorEventRepository.findByCreatedAtBetween(from, to, pageable);
        }

        return errorEventRepository.findAll(pageable);
    }
}
