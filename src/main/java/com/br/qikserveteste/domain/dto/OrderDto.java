package com.br.qikserveteste.domain.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    public OrderDto(List<ItemDto> items, BigDecimal total, BigDecimal totalDiscount) {
        this.items = items;
        this.total = total;
        this.totalDiscount = totalDiscount;
    }

    private String id = UUID.randomUUID().toString();
    private List<ItemDto> items = new ArrayList<>();
    private BigDecimal total = BigDecimal.ZERO;
    private BigDecimal totalDiscount = BigDecimal.ZERO;

}
