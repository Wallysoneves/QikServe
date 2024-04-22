package com.br.qikserveteste.infrastructure.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ErroModel {

    private Integer status;
    private String erro;
    private LocalDateTime timestamp;
}
