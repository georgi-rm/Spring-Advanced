package com.example.airlinetickets.services;

import com.example.airlinetickets.models.dtos.LogAddDto;
import com.example.airlinetickets.models.entities.LogEntity;
import com.example.airlinetickets.repositories.LogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogService {

    private final LogRepository logRepository;

    private final ModelMapper modelMapper;

    public LogService(LogRepository logRepository, ModelMapper modelMapper) {
        this.logRepository = logRepository;
        this.modelMapper = modelMapper;
    }

    public void addLog(LogAddDto logAddDto) {
        LogEntity logEntity = modelMapper.map(logAddDto, LogEntity.class);

        logEntity.setDateTime(LocalDateTime.now());

        logRepository.save(logEntity);
    }
}
