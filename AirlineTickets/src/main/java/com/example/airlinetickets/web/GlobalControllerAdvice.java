package com.example.airlinetickets.web;

import com.example.airlinetickets.exceptions.BadQueryParametersException;
import com.example.airlinetickets.exceptions.OperationNotAllowedException;
import com.example.airlinetickets.exceptions.TicketNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
    @ExceptionHandler({UsernameNotFoundException.class, TicketNotFoundException.class,
            OperationNotAllowedException.class})
    public ModelAndView onGeneralException(Exception exception) {

        ModelAndView modelAndView = new ModelAndView("errors/exception");
        modelAndView.addObject("exceptionText", exception.getMessage());
        return modelAndView;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadQueryParametersException.class)
    public ModelAndView onRequestWithBadQueryParameters(BadQueryParametersException badQueryParametersException) {

        ModelAndView modelAndView = new ModelAndView("errors/bad-query-parameters");

        modelAndView.addObject("errorCount", badQueryParametersException.getBindingResult().getAllErrors().size());

        return modelAndView;
    }
}
