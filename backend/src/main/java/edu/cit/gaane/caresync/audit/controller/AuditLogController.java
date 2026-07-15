package edu.cit.gaane.caresync.audit.controller;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.cit.gaane.caresync.audit.entity.AuditLog;
import edu.cit.gaane.caresync.audit.repository.AuditLogRepository;

@RestController
@RequestMapping("/api/audit")
public class AuditLogController {

    private final AuditLogRepository repository;

    public AuditLogController(AuditLogRepository repository) {
        this.repository = repository;
    }

    @GetMapping
public List<AuditLog> getLogs() {
    return repository.findAll(
            Sort.by(Sort.Direction.DESC, "createdAt")
    );
}
}