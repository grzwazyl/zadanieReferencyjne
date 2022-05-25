package com.zyzz.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(KolizjaRezerwacjiException.class)
    public ErrorMsg kolizjaException(KolizjaRezerwacjiException exception) {
        ErrorMsg errorMsg = new ErrorMsg();
        errorMsg.setTimestamp(LocalDateTime.now());
        errorMsg.setWiadomosc(exception.getMessage());
        errorMsg.setHttpStatus(HttpStatus.BAD_REQUEST);

        return errorMsg;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(ZlaDataRezerwacjiException.class)
    public ErrorMsg zlaDataException(ZlaDataRezerwacjiException exception) {
        ErrorMsg errorMsg = new ErrorMsg();
        errorMsg.setTimestamp(LocalDateTime.now());
        errorMsg.setWiadomosc(exception.getMessage());
        errorMsg.setHttpStatus(HttpStatus.BAD_REQUEST);

        return errorMsg;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMsg validationException(MethodArgumentNotValidException exception){
        ErrorMsg errorMsg = new ErrorMsg();
        errorMsg.setTimestamp(LocalDateTime.now());
        errorMsg.setWiadomosc(exception.getMessage());
        errorMsg.setHttpStatus(HttpStatus.BAD_REQUEST);

        return errorMsg;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ErrorMsg exception(Exception exception){
        ErrorMsg errorMsg = new ErrorMsg();
        errorMsg.setTimestamp(LocalDateTime.now());
        errorMsg.setWiadomosc(exception.getMessage());
        errorMsg.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return errorMsg;
    }
}
