package com.br.qikserveteste.product;

import com.br.qikserveteste.domain.Product;
import com.br.qikserveteste.infrastructure.exception.QikServeException;
import com.br.qikserveteste.infrastructure.route.WireMockProduct;
import com.br.qikserveteste.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest {

    @Mock
    private WireMockProduct wireMockProduct;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll_Success() {
        List<Product> products = Arrays.asList(
                Product.builder().id("Dwt5F7KAhi").name("Amazing Pizza!").price(new BigDecimal(1099)).build(),
                Product.builder().id("PWWe3w1SDU").name("Amazing Burger!").price(new BigDecimal(999)).build(),
                Product.builder().id("C8GDyLrHJb").name("Amazing Salad!").price(new BigDecimal(499)).build(),
                Product.builder().id("4MB7UfpTQs").name("Boring Fries!").price(new BigDecimal(199)).build()
        );

        ResponseEntity<List<Product>> responseEntity = ResponseEntity.ok(products);
        when(wireMockProduct.getAll()).thenReturn(responseEntity);

        List<Product> result = productService.getAll();
        verify(wireMockProduct, times(1)).getAll();
        assertEquals(products, result);
    }

    @Test
    public void testGetAll_ApiDown() {
        when(wireMockProduct.getAll()).thenThrow(new RuntimeException("API WireMock not found"));

        QikServeException exeception = assertThrows(QikServeException.class, () -> productService.getAll(), "API down");
        assertEquals(HttpStatus.BAD_REQUEST, exeception.getStatus());
    }

    @Test
    public void testGetById_Success() {
        Product product = Product.builder()
                .id("Dwt5F7KAhi")
                .name("Amazing Pizza!")
                .price(new BigDecimal(1099))
                .build();

        ResponseEntity<Product> responseEntity = ResponseEntity.ok(product);
        when(wireMockProduct.getById("Dwt5F7KAhi")).thenReturn(responseEntity);

        Product result = productService.getById("Dwt5F7KAhi");
        verify(wireMockProduct, times(1)).getById("Dwt5F7KAhi");
        assertEquals(product, result);
    }

    @Test
    public void testGetById_ProductNotFound() {
        String id = "Dwt5F7KAha";

        when(wireMockProduct.getById(id)).thenThrow(new RuntimeException("Product not found"));
        QikServeException exeception = assertThrows(QikServeException.class, () -> productService.getById(id), "Product not found");
        assertEquals(HttpStatus.NOT_FOUND, exeception.getStatus());
    }
}
