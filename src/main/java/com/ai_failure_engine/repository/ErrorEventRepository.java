package com.ai_failure_engine.repository;

import com.ai_failure_engine.model.ErrorEvent;
import com.ai_failure_engine.model.Severity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


@Repository
public interface ErrorEventRepository extends JpaRepository<ErrorEvent, Long> {
    Page<ErrorEvent> findByCreatedAtBetween(Instant from, Instant to, Pageable pageable);
    @Query("""
    SELECT COUNT(e)
    FROM ErrorEvent e
    WHERE e.serviceName = :serviceName
      AND e.severity IN :severities
      AND e.createdAt >= :fromTime
""")
    long countRecentHighSeverityEvents(
            @Param("serviceName") String serviceName,
            @Param("severities") List<Severity> severities,
            @Param("fromTime") Instant fromTime
    );

}

