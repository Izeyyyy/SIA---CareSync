package edu.cit.gaane.caresync.audit.controller;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.cit.gaane.caresync.audit.dto.MyActivityResponse;
import edu.cit.gaane.caresync.audit.entity.AuditLog;
import edu.cit.gaane.caresync.audit.repository.AuditLogRepository;
import edu.cit.gaane.caresync.audit.service.AuditLogService;
import edu.cit.gaane.caresync.audit.dto.MyActivityResponse;

@RestController
@RequestMapping("/api/audit")
public class AuditLogController {

    private final AuditLogRepository repository;
    private final AuditLogService auditLogService;

    public AuditLogController(AuditLogRepository repository, AuditLogService auditLogService) {
        this.repository = repository;
        this.auditLogService = auditLogService;
    }

    @GetMapping
    public List<AuditLog> getLogs() {
        return repository.findAll(
                Sort.by(Sort.Direction.DESC, "createdAt")
        );
    }

    @GetMapping("/my-activity")
    public List<MyActivityResponse> getMyActivity(){

        return auditLogService.getMyActivity();

    }
}