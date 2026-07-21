package edu.cit.gaane.caresync.audit.dto;

import java.time.LocalDateTime;

public class MyActivityResponse {

    private Long id;
    private String action;
    private String module;
    private String description;
    private LocalDateTime createdAt;

    public MyActivityResponse(
            Long id,
            String action,
            String module,
            String description,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.action = action;
        this.module = module;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public String getModule() {
        return module;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}