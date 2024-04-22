package com.br.qikserveteste.domain.dto;

import com.br.qikserveteste.domain.Product;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private String id;
    private String name;
    private Integer qty;
    private BigDecimal price;
    private BigDecimal discount;
    private BigDecimal subTotal;
}
