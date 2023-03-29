package com.example.airlinetickets.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
public class LogEntity extends BaseEntity {

    String url;

    @Column(name = "execution_time_ms")
    Long executionTimeMs;

    String user;

    @Column(name = "date_time")
    LocalDateTime dateTime;

    public String getUrl() {
        return url;
    }

    public LogEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    public Long getExecutionTimeMs() {
        return executionTimeMs;
    }

    public LogEntity setExecutionTimeMs(Long executionTimeMs) {
        this.executionTimeMs = executionTimeMs;
        return this;
    }

    public String getUser() {
        return user;
    }

    public LogEntity setUser(String user) {
        this.user = user;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LogEntity setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }
}
