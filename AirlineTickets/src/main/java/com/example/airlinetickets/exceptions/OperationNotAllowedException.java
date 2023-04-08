package com.example.airlinetickets.exceptions;

public class OperationNotAllowedException extends RuntimeException {

    public OperationNotAllowedException() {

        super("You are not allowed to do this!");

    }
}
