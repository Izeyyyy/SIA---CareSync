package edu.cit.gaane.caresync.audit.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import edu.cit.gaane.caresync.audit.entity.AuditLog;


public interface AuditLogRepository 
        extends JpaRepository<AuditLog, Long> {

    List<AuditLog> findByUserIdAndActionNotOrderByCreatedAtDesc(Long userId, String action);

}