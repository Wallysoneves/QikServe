package com.br.qikserveteste.resource.implementation;

import com.br.qikserveteste.domain.dto.OrderDto;
import com.br.qikserveteste.domain.dto.ProductDto;
import com.br.qikserveteste.resource.OrderController;
import com.br.qikserveteste.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    @PostMapping(
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )
    public ResponseEntity<OrderDto> create(@RequestBody List<ProductDto> productsDto) {
        return new ResponseEntity<>(orderService.create(productsDto), HttpStatus.CREATED);
    }

    @Override
    @GetMapping(value = "/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )
    public ResponseEntity<OrderDto> getById(@PathVariable("id")  @Validated String id) {
        return new ResponseEntity<>(orderService.getById(id), HttpStatus.OK);
    }

    @Override
    @PatchMapping(value = "/{id}",
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )
    public ResponseEntity<OrderDto> update(@PathVariable("id") String id, @RequestBody List<ProductDto> productsDto) {
        return new ResponseEntity<>(orderService.update(id, productsDto), HttpStatus.OK);
    }
}
