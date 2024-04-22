package com.br.qikserveteste.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(QikServeException.class)
    public ResponseEntity<ErroModel> handleQikServeException(QikServeException ex) {
        HttpStatus status = Objects.nonNull(ex.getStatus()) ? ex.getStatus() : HttpStatus.BAD_REQUEST;

        ErroModel apiErrorModel = ErroModel.builder()
                .status(status.value())
                .erro(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(apiErrorModel, status);
    }
}
