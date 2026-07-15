package edu.cit.gaane.caresync.audit.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "audit_logs")
public class AuditLog {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /*
        User who performed the action
     */
    private Long userId;


    private String username;


    private String role;


    /*
        CREATE, UPDATE, DELETE, LOGIN, LOGOUT
     */
    private String action;


    /*
        PATIENT, USER, CONSULTATION, MEDICAL_RECORD
     */
    private String module;


    private String description;


    /*
        ID of affected object
        Example:
        patientId = 15
     */
    private Long targetId;


    private LocalDateTime createdAt;


    @PrePersist
    public void onCreate(){

        createdAt = LocalDateTime.now();

    }

}