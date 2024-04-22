package com.br.qikserveteste.resource;

import com.br.qikserveteste.domain.Product;
import com.br.qikserveteste.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tags({
        @Tag(name = "product", description = "documentation for the products resource")
})
public class ProductControllerImpl implements ProductController{

    @Autowired
    private ProductService productService;

    @GetMapping(
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "/{id}/promotions",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )
    public ResponseEntity<Product> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
    }
}
