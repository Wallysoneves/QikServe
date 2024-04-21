package com.br.qikserveteste.domain;

import com.br.qikserveteste.domain.enums.TypePromotion;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Promotion {

    private String id;

    @Enumerated(EnumType.STRING)
    private TypePromotion type;

    private BigDecimal amount;
    private Integer requiredQty;
    private BigDecimal price;
    private Integer freeQty;
}
