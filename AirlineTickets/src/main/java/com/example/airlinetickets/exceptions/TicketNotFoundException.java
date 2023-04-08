package com.example.airlinetickets.exceptions;

public class TicketNotFoundException extends RuntimeException {

    public TicketNotFoundException(Long ticketId) {

        super("Ticket with ID: " + ticketId + " was not found !");
    }
}
