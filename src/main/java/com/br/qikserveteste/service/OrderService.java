package com.br.qikserveteste.service;

import com.br.qikserveteste.domain.dto.OrderDto;
import com.br.qikserveteste.domain.dto.ProductDto;

import java.util.List;

public interface OrderService {

    OrderDto create(List<ProductDto> products);

    OrderDto getById(String id);
}
