package com.ai_failure_engine.repository;

import com.ai_failure_engine.model.ErrorEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.List;

@Repository
public interface ErrorEventRepository extends JpaRepository<ErrorEvent, Long> {
    List<ErrorEvent> findByCreatedAtBetween(Instant from, Instant to);
}

