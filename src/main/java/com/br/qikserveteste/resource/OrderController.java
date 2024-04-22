package com.br.qikserveteste.resource;

import com.br.qikserveteste.domain.dto.OrderDto;
import com.br.qikserveteste.domain.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OrderController {

    ResponseEntity<OrderDto> create(@RequestBody List<ProductDto> productsDto);

    ResponseEntity<OrderDto> getById(@PathVariable("id") @Validated String id);

    ResponseEntity<OrderDto> update(@PathVariable("id") @Validated String id, @RequestBody List<ProductDto> productsDto);

}
