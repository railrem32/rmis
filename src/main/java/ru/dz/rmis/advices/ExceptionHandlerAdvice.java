package ru.dz.rmis.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Alex on 23.08.16.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "Exception occurred please check log files")
    @ExceptionHandler(Exception.class)
    void handleAnyException() {}
}
