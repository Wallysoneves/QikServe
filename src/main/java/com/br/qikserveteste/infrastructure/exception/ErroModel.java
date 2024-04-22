package com.br.qikserveteste.infrastructure.exception;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErroModel(Integer status, String erro, LocalDateTime timestamp) {
}
