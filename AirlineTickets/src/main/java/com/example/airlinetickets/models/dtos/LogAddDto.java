package com.example.airlinetickets.models.dtos;

public class LogAddDto {

    String url;

    Long executionTimeMs;

    String user;

    public String getUrl() {
        return url;
    }

    public LogAddDto setUrl(String url) {
        this.url = url;
        return this;
    }

    public Long getExecutionTimeMs() {
        return executionTimeMs;
    }

    public LogAddDto setExecutionTimeMs(Long executionTimeMs) {
        this.executionTimeMs = executionTimeMs;
        return this;
    }

    public String getUser() {
        return user;
    }

    public LogAddDto setUser(String user) {
        this.user = user;
        return this;
    }
}
