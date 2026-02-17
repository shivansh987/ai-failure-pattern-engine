package com.ai_failure_engine.repository;

import com.ai_failure_engine.model.ErrorEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ErrorEventRepository extends JpaRepository<ErrorEvent, Long> {
    Page<ErrorEvent> findByCreatedAtBetween(Instant from, Instant to, Pageable pageable);
}

