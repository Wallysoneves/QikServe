package com.br.qikserveteste.service.strategy;

import com.br.qikserveteste.domain.Product;
import com.br.qikserveteste.domain.Promotion;
import com.br.qikserveteste.domain.dto.ItemDto;
import com.br.qikserveteste.domain.dto.ProductDto;
import com.br.qikserveteste.service.PromotionStrategy;

import java.math.BigDecimal;

public class PercentageService implements PromotionStrategy {

    @Override
    public ItemDto calculate(ProductDto productDto, Product product, Promotion promotion) {

        BigDecimal totalItem =  product.getPrice().multiply(new BigDecimal(productDto.qty())).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal discount = totalItem.multiply( (promotion.getAmount().divide(new BigDecimal(100))) ).setScale(2);

        return ItemDto.builder()
                .id(productDto.id())
                .name(product.getName())
                .qty(productDto.qty())
                .price(product.getPrice())
                .discount(discount)
                .subTotal(totalItem)
                .build();
    }
}
