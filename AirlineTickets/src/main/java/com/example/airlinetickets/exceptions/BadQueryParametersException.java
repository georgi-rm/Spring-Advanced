package com.example.airlinetickets.exceptions;

import org.springframework.validation.BindingResult;

public class BadQueryParametersException extends RuntimeException {

    private final BindingResult bindingResult;

    public BadQueryParametersException(BindingResult bindingResult) {

        super("Count of errors in query string: " + bindingResult.getAllErrors().size());

        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
