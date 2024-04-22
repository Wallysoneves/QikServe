package com.br.qikserveteste.service.strategy;

import com.br.qikserveteste.domain.Product;
import com.br.qikserveteste.domain.Promotion;
import com.br.qikserveteste.domain.dto.ItemDto;
import com.br.qikserveteste.domain.dto.ProductDto;
import com.br.qikserveteste.service.PromotionStrategy;

import java.math.BigDecimal;

public class GiftService implements PromotionStrategy {

    @Override
    public ItemDto calculate(ProductDto productDto, Product product, Promotion promotion) {

        BigDecimal totalItem = product.getPrice().multiply(BigDecimal.valueOf(productDto.qty())).setScale(2, BigDecimal.ROUND_HALF_UP);

        ItemDto item = ItemDto.builder()
                .id(productDto.id())
                .name(product.getName())
                .qty(productDto.qty())
                .price(product.getPrice())
                .discount(BigDecimal.ZERO)
                .subTotal(totalItem).build();

        if (productDto.qty() >= promotion.getRequiredQty()) {
            Integer qtyGift = productDto.qty() / (promotion.getRequiredQty()) * promotion.getFreeQty();

            BigDecimal discount =  product.getPrice().multiply(BigDecimal.valueOf(qtyGift));
            item.setDiscount(discount);
        }

        return item;
    }
}
