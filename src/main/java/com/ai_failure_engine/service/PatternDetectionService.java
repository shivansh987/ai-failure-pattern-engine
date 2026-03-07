package com.ai_failure_engine.service;

import com.ai_failure_engine.model.Severity;
import com.ai_failure_engine.repository.ErrorEventRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
@Service
public class PatternDetectionService {
    @Value("${alert.spike.threshold}")
    private int threshold;

    @Value("${alert.spike.window.seconds}")
    private int windowSeconds;
    private final ErrorEventRepository repository;

    public PatternDetectionService(ErrorEventRepository repository) {
        this.repository = repository;
    }

    public long countRecentHighSeverity(String serviceName) {

        Instant windowStart = Instant.now().minusSeconds(windowSeconds);

        return repository.countRecentHighSeverityEvents(
                serviceName,
                List.of(Severity.HIGH, Severity.CRITICAL),
                windowStart
        );
    }

}
