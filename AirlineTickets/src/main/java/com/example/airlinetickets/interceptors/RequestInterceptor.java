package com.example.airlinetickets.interceptors;

import com.example.airlinetickets.models.dtos.LogAddDto;
import com.example.airlinetickets.services.LogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    private final LogService logService;

    private final String START_TIME = "startTime";

    public RequestInterceptor(LogService logService) {
        this.logService = logService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Long startTime = System.currentTimeMillis();
        request.setAttribute(START_TIME, startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        Long endTime = System.currentTimeMillis();
        Long startTime = (Long) request.getAttribute(START_TIME);
        Long executionTime = endTime - startTime;

        LogAddDto logAddDto = new LogAddDto()
                .setUrl(request.getRequestURI())
                .setExecutionTimeMs(executionTime)
                .setUser(request.getUserPrincipal() == null ? "Anonymous" : request.getUserPrincipal().getName());


        if (request.getMethod().equalsIgnoreCase("DELETE")) {
            logService.addLog(logAddDto);
        }
    }
}
