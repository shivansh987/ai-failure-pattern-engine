package com.ai_failure_engine.repository;

import com.ai_failure_engine.model.Alert;
import com.ai_failure_engine.model.AlertStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    Optional<Alert> findByServiceNameAndStatus(String serviceName, AlertStatus status);

    Page<Alert> findByStatus(AlertStatus status, Pageable pageable);

    Page<Alert> findByServiceName(String serviceName, Pageable pageable);

    Page<Alert> findByServiceNameAndStatus(String serviceName, AlertStatus status, Pageable pageable);
}