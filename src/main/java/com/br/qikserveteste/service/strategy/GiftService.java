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
            Integer qtyPaid = productDto.qty() - (productDto.qty() / (promotion.getRequiredQty() + promotion.getFreeQty())) * promotion.getFreeQty();
            Integer qtyGift = productDto.qty() / (promotion.getRequiredQty() + promotion.getFreeQty()) * promotion.getFreeQty();

            BigDecimal subTotalPaid = product.getPrice().multiply(BigDecimal.valueOf(qtyPaid));
            BigDecimal subTotalGift = BigDecimal.ZERO;

            if (qtyGift > 0) {
                subTotalGift = product.getPrice().multiply(BigDecimal.valueOf(qtyGift));
            }

            BigDecimal total = subTotalPaid.add(subTotalGift).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal discount = subTotalGift;

            item.setSubTotal(total);
            item.setDiscount(discount);
        }

        return item;
    }
}
