package com.br.qikserveteste.infrastructure.route;

import com.br.qikserveteste.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "${wiremock.url}", name = "product")
public interface WireMockProduct {

    @GetMapping(value = "/products")
    ResponseEntity<List<Product>> getAll();

    @GetMapping(value = "/products/{id}")
    ResponseEntity<Product> getById(@PathVariable("id") String id);
}
