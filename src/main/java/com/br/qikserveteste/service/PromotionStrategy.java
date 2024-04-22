package com.br.qikserveteste.service;

import com.br.qikserveteste.domain.Product;
import com.br.qikserveteste.domain.Promotion;
import com.br.qikserveteste.domain.dto.ItemDto;
import com.br.qikserveteste.domain.dto.ProductDto;

public interface PromotionStrategy {

    ItemDto calculate(ProductDto productDto, Product product, Promotion promotion);
}
