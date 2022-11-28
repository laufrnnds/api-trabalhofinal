package com.growdev.trabalhofinal.exceptions;

import com.growdev.trabalhofinal.services.exceptions.BadRequestException;
import com.growdev.trabalhofinal.services.exceptions.DataBaseException;
import com.growdev.trabalhofinal.services.exceptions.EntityNotFoundIdException;
import com.growdev.trabalhofinal.services.exceptions.InternalServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceControllerException {

    @ExceptionHandler(EntityNotFoundIdException.class)
    public ResponseEntity<StandardError> entityNotFoundIdException(EntityNotFoundIdException e, HttpServletRequest request) {

        StandardError error = new StandardError();
        HttpStatus statusError = HttpStatus.NOT_FOUND;
        error.setTimestamp(Instant.now());
        error.setStatus(statusError.value());
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        error.setError("Recurso não encontrado");

        return ResponseEntity.status(statusError).body(error);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardError> badRequestException(BadRequestException e, HttpServletRequest request) {

        StandardError error = new StandardError();
        HttpStatus statusError = HttpStatus.BAD_REQUEST;
        error.setTimestamp(Instant.now());
        error.setStatus(statusError.value());
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        error.setError("Requisição feita de forma indevida");

        return ResponseEntity.status(statusError).body(error);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<StandardError> internalServerErrorException(InternalServerErrorException e, HttpServletRequest request) {

        StandardError error = new StandardError();
        HttpStatus statusError = HttpStatus.INTERNAL_SERVER_ERROR;
        error.setTimestamp(Instant.now());
        error.setStatus(statusError.value());
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        error.setError("Erro interno no servidor");

        return ResponseEntity.status(statusError).body(error);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandardError> dataBaseException(DataBaseException e, HttpServletRequest request) {

        StandardError error = new StandardError();
        HttpStatus statusError = HttpStatus.BAD_REQUEST;
        error.setTimestamp(Instant.now());
        error.setStatus(statusError.value());
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        error.setError("DataBaseException");

        return ResponseEntity.status(statusError).body(error);
    }



}
