package com.zyzz.main.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorMsg {

    private LocalDateTime timestamp;

    private String wiadomosc;

    private HttpStatus httpStatus;
}
