package com.example.airlinetickets.repositories;

import com.example.airlinetickets.models.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    List<TicketEntity> findAllByUserUsername(String username);
}
