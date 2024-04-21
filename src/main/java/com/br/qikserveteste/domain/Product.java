package com.br.qikserveteste.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    private String id;
    private String name;
    private BigDecimal price;
    private List<Promotion> promotions = new ArrayList<>();
}
