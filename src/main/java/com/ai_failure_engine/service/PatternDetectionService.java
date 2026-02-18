package com.ai_failure_engine.service;
import com.ai_failure_engine.model.Severity;
import com.ai_failure_engine.repository.ErrorEventRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
@Service
public class PatternDetectionService {

    private final ErrorEventRepository repository;

    public PatternDetectionService(ErrorEventRepository repository) {
        this.repository = repository;
    }

    public boolean isSpikeDetected(String serviceName) {

        Instant twoMinutesAgo = Instant.now().minusSeconds(120);

        long count = repository.countRecentHighSeverityEvents(
                serviceName,
                List.of(Severity.HIGH, Severity.CRITICAL),
                twoMinutesAgo
        );

        return count > 5;
    }
}
