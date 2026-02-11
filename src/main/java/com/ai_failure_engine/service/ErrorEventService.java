package com.ai_failure_engine.service;

import com.ai_failure_engine.model.ErrorEvent;
import com.ai_failure_engine.repository.ErrorEventRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class ErrorEventService {

    private final ErrorEventRepository errorEventRepository;

    public ErrorEventService(ErrorEventRepository errorEventRepository) {
        this.errorEventRepository = errorEventRepository;
    }

    @Transactional
    public ErrorEvent save(ErrorEvent errorEvent) {
        ErrorEvent saved = errorEventRepository.save(errorEvent);
        System.out.println("Saved ID: " + saved.getId());
        return saved;
    }

    public List<ErrorEvent> getAllEvents() {
        return errorEventRepository.findAll();
    }
    public List<ErrorEvent> getEventsByTimeRange(Instant from, Instant to) {
        return errorEventRepository.findByCreatedAtBetween(from, to);
    }
}
