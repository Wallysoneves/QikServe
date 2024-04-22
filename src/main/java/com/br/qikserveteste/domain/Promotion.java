package com.br.qikserveteste.domain;

import com.br.qikserveteste.domain.enums.TypePromotion;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {

    private String id;

    @Enumerated(EnumType.STRING)
    private TypePromotion type;

    private BigDecimal amount;

    @JsonProperty("required_qty")
    private Integer requiredQty;
    private BigDecimal price;

    @JsonProperty("free_qty")
    private Integer freeQty;
}
