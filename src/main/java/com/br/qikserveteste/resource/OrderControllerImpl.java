package com.br.qikserveteste.resource;

import com.br.qikserveteste.domain.dto.OrderDto;
import com.br.qikserveteste.domain.dto.ProductDto;
import com.br.qikserveteste.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderControllerImpl implements OrderController {

    @Autowired
    private OrderService orderService;

    @Override
    @PostMapping("/save")
    public ResponseEntity<OrderDto> create(@RequestBody List<ProductDto> productsDto) {
        return new ResponseEntity<>(orderService.create(productsDto), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable("id")  @Validated String id) {
        return new ResponseEntity<>(orderService.getById(id), HttpStatus.OK);
    }

    @Override
    @PatchMapping("/update/{id}")
    public ResponseEntity<OrderDto> update(@PathVariable("id") String id, @RequestBody List<ProductDto> productsDto) {
        return new ResponseEntity<>(orderService.update(id, productsDto), HttpStatus.OK);
    }
}
