package com.zyzz.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(KolizjaRezerwacjiException.class)
    public ErrorMsg kolizjaException(KolizjaRezerwacjiException exception) {
        ErrorMsg errorMsg = new ErrorMsg();
        errorMsg.setTimestamp(LocalDateTime.now());
        errorMsg.setWiadomosc(exception.getMessage());
        errorMsg.setHttpStatus(HttpStatus.BAD_REQUEST);

        return errorMsg;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ZlaDataRezerwacjiException.class)
    public ErrorMsg zlaDataException(ZlaDataRezerwacjiException exception) {
        ErrorMsg errorMsg = new ErrorMsg();
        errorMsg.setTimestamp(LocalDateTime.now());
        errorMsg.setWiadomosc(exception.getMessage());
        errorMsg.setHttpStatus(HttpStatus.BAD_REQUEST);

        return errorMsg;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMsg validationException(MethodArgumentNotValidException exception){
        ErrorMsg errorMsg = new ErrorMsg();
        errorMsg.setTimestamp(LocalDateTime.now());
        errorMsg.setWiadomosc(exception.getMessage());
        errorMsg.setHttpStatus(HttpStatus.BAD_REQUEST);

        return errorMsg;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ErrorMsg exception(Exception exception){
        ErrorMsg errorMsg = new ErrorMsg();
        errorMsg.setTimestamp(LocalDateTime.now());
        errorMsg.setWiadomosc(exception.getMessage());
        errorMsg.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return errorMsg;
    }
}
