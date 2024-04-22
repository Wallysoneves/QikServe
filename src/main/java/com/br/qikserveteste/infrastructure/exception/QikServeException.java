package com.br.qikserveteste.infrastructure.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class QikServeException extends RuntimeException {

    private HttpStatus status;
    private Object retorno;

    public QikServeException(String message) {
        super(message);
    }

    public QikServeException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public QikServeException(String message, HttpStatus status, Object retorno) {
        super(message);
        this.status = status;
        this.retorno = retorno;
    }
}
