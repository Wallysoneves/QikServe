package com.br.qikserveteste.resource;

import com.br.qikserveteste.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductController {

    ResponseEntity<List<Product>> getAll();

    ResponseEntity<Product> getById(@PathVariable("id") String id);
}
