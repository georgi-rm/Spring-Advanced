package com.example.airlinetickets.services;

import com.example.airlinetickets.exceptions.OperationNotAllowedException;
import com.example.airlinetickets.exceptions.TicketNotFoundException;
import com.example.airlinetickets.models.dtos.binding.ReserveTicketDto;
import com.example.airlinetickets.models.dtos.view.TicketViewDto;
import com.example.airlinetickets.models.entities.TicketEntity;
import com.example.airlinetickets.models.mapper.TicketMapper;
import com.example.airlinetickets.repositories.TicketRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    private final UserService userService;

    private final TicketMapper ticketMapper;

    public TicketService(TicketRepository ticketRepository, UserService userService, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
        this.ticketMapper = ticketMapper;
    }

    public void reserve(ReserveTicketDto reserveTicketDto,
                        UserDetails userDetails) {
        TicketEntity ticketEntity = ticketMapper.reserveTicketDtoToTicketEntity(reserveTicketDto);

        ticketEntity.setUser(userService.getUserEntityByUsername(userDetails.getUsername()));

        ticketRepository.save(ticketEntity);
    }

    public List<TicketViewDto> getAllTicketsByUsername(String username) {
        return ticketRepository
                .findAllByUserUsername(username)
                .stream().map(ticketMapper::flightEntityToFlightViewDto)
                .toList();
    }

    public void deleteTicketById(Long id, UserDetails userDetails) {

        Optional<TicketEntity> ticketEntity = ticketRepository.findById(id);

        if (ticketEntity.isEmpty()) {
            throw new TicketNotFoundException(id);
        }

        if (!ticketEntity.get().getUser().getUsername().equals(userDetails.getUsername())) {
            throw new OperationNotAllowedException();
        }

        ticketRepository.delete(ticketEntity.get());
    }

    public void deleteAllTicketsByFlightId(Long id) {
        ticketRepository.deleteAll(ticketRepository.findAllByFlightId(id));
    }
}
