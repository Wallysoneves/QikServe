package com.br.qikserveteste.service.strategy;

import com.br.qikserveteste.domain.Product;
import com.br.qikserveteste.domain.Promotion;
import com.br.qikserveteste.domain.dto.ItemDto;
import com.br.qikserveteste.domain.dto.ProductDto;
import com.br.qikserveteste.service.PromotionStrategy;

import java.math.BigDecimal;

public class DiscountService implements PromotionStrategy {

    @Override
    public ItemDto calculate(ProductDto productDto, Product product, Promotion promotion) {

        BigDecimal totalItem = product.getPrice().multiply(new BigDecimal(productDto.qty())).setScale(2, BigDecimal.ROUND_HALF_UP);

        ItemDto item = ItemDto.builder()
                .id(productDto.id())
                .name(product.getName())
                .qty(productDto.qty())
                .price(product.getPrice())
                .discount(BigDecimal.ZERO)
                .subTotal(totalItem)
                .build();

        if (productDto.qty() >= promotion.getRequiredQty()) {
            Integer itemsPairs = productDto.qty() / 2; //Quantidade de pares elegivel para essa promoção

            BigDecimal totalPriceWithDiscount = promotion.getPrice().multiply(new BigDecimal(itemsPairs)).setScale(2, BigDecimal.ROUND_HALF_UP);

            BigDecimal discount = totalItem.subtract(totalPriceWithDiscount);

            if (productDto.qty() % 2 != 0) {
                BigDecimal lastPizzaPrice = product.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP);

                discount = discount.subtract(lastPizzaPrice);
            }

            item.setDiscount(discount);
        }

        return item;
    }
}
