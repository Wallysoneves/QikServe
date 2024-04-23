package com.br.qikserveteste.order;

import com.br.qikserveteste.domain.Product;
import com.br.qikserveteste.domain.dto.OrderDto;
import com.br.qikserveteste.domain.dto.ProductDto;
import com.br.qikserveteste.factory.ObjectsTest;
import com.br.qikserveteste.infrastructure.exception.QikServeException;
import com.br.qikserveteste.infrastructure.route.WireMockProduct;
import com.br.qikserveteste.service.implementation.OrderServiceImpl;
import com.br.qikserveteste.service.strategy.DiscountService;
import com.br.qikserveteste.service.strategy.GiftService;
import com.br.qikserveteste.service.strategy.PercentageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderTest {

    @Mock
    private WireMockProduct wireMockProduct;

    @Autowired
    private OrderServiceImpl orderService;
    @Mock
    private PercentageService percentageService;
    @Mock
    private GiftService giftService;
    @Mock
    private DiscountService discountService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void create_EmptyProductList_ShouldThrowException() {
        List<ProductDto> emptyProductList = Collections.emptyList();
        assertThrows(QikServeException.class, () -> orderService.create(emptyProductList));
    }

    @Test
    void create_ProductWithNoPromotion_ShouldNotCalculateDiscount() {
        Product product = ObjectsTest.createProduct();
        OrderDto order = ObjectsTest.createOrderDto();

        ResponseEntity<Product> responseProduct = ResponseEntity.ok(product);
        when(wireMockProduct.getById("4MB7UfpTQs")).thenReturn(responseProduct);

        ProductDto productDto = ObjectsTest.createProductDto();
        OrderDto actualOrder = orderService.create(Collections.singletonList(productDto));

        assertEquals(order.getTotal(), actualOrder.getTotal());
        assertNotNull(orderService.getById(actualOrder.getId()));
    }

    @Test
    void create_ProductWithPromotion_ShouldCalculateGift() {
        Product product = ObjectsTest.createProductGift();
        OrderDto order = ObjectsTest.createOrderDtoGift();

        ResponseEntity<Product> responseProduct = ResponseEntity.ok(product);
        when(wireMockProduct.getById("PWWe3w1SDU")).thenReturn(responseProduct);

        ProductDto productDto = ObjectsTest.createProductDtoGift();
        OrderDto actualOrder = orderService.create(Collections.singletonList(productDto));

        assertEquals(order.getTotal(), actualOrder.getTotal());
        assertEquals(order.getTotalDiscount(), actualOrder.getTotalDiscount());
        assertNotNull(orderService.getById(actualOrder.getId()));
        verify(giftService, never()).calculate(any(), any(), any());
    }

    @Test
    void create_ProductWithPromotion_ShouldCalculateDiscount() {
        Product product = ObjectsTest.createProductDiscount();
        OrderDto order = ObjectsTest.createOrderDtoDiscount();

        ResponseEntity<Product> responseProduct = ResponseEntity.ok(product);
        when(wireMockProduct.getById("Dwt5F7KAhi")).thenReturn(responseProduct);

        ProductDto productDto = ObjectsTest.createProductDtoDiscount();
        OrderDto actualOrder = orderService.create(Collections.singletonList(productDto));

        assertEquals(order.getTotal(), actualOrder.getTotal());
        assertEquals(order.getTotalDiscount(), actualOrder.getTotalDiscount());
        assertNotNull(orderService.getById(actualOrder.getId()));
        verify(discountService, never()).calculate(any(), any(), any());
    }

    @Test
    void create_ProductWithPromotion_ShouldCalculatePercentage() {
        Product product = ObjectsTest.createProductPercentage();
        OrderDto order = ObjectsTest.createOrderDtoPercentage();

        ResponseEntity<Product> responseProduct = ResponseEntity.ok(product);
        when(wireMockProduct.getById("C8GDyLrHJb")).thenReturn(responseProduct);

        ProductDto productDto = ObjectsTest.createProductDtoPercentage();
        OrderDto actualOrder = orderService.create(Collections.singletonList(productDto));

        assertEquals(order.getTotal(), actualOrder.getTotal());
        assertEquals(order.getTotalDiscount(), actualOrder.getTotalDiscount());
        assertNotNull(orderService.getById(actualOrder.getId()));
        verify(percentageService, never()).calculate(any(), any(), any());
    }

    @Test
    void create_ProductWithPromotion_ShouldCalculateAll() {
        List<Product> products = ObjectsTest.createProductAll();
        OrderDto order = ObjectsTest.createOrderDtoAll();

        for (Product product : products) {
            ResponseEntity<Product> responseProduct = ResponseEntity.ok(product);
            when(wireMockProduct.getById(product.getId())).thenReturn(responseProduct);
        }

        List<ProductDto> productsDto = ObjectsTest.createProductDtoAll();
        OrderDto actualOrder = orderService.create(productsDto);

        assertEquals(order.getTotal(), actualOrder.getTotal());
        assertEquals(order.getTotalDiscount(), actualOrder.getTotalDiscount());
        assertNotNull(orderService.getById(actualOrder.getId()));
        verify(giftService, never()).calculate(any(), any(), any());
        verify(discountService, never()).calculate(any(), any(), any());
        verify(percentageService, never()).calculate(any(), any(), any());
    }

    @Test
    void getById_OrderNotExists() {
        String id = "123";

        QikServeException exception = assertThrows(QikServeException.class, () -> orderService.getById(id), "order not found");
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    void update_OrderWithPromotion_ShowCalculateDiscount() {
        Product product = ObjectsTest.createProductDiscount();
        OrderDto order = ObjectsTest.createOrderDtoDiscount();

        ResponseEntity<Product> responseProduct = ResponseEntity.ok(product);
        when(wireMockProduct.getById("Dwt5F7KAhi")).thenReturn(responseProduct);

        ProductDto productDto = ObjectsTest.createProductDtoDiscount();
        OrderDto actualOrder = orderService.create(Collections.singletonList(productDto));

        assertEquals(order.getTotal(), actualOrder.getTotal());
        assertEquals(order.getTotalDiscount(), actualOrder.getTotalDiscount());
        assertNotNull(orderService.getById(actualOrder.getId()));
        verify(discountService, never()).calculate(any(), any(), any());

        ProductDto productDtoPercentage = ObjectsTest.createProductDtoPercentage();
        OrderDto actualOrderPercentage = orderService.update(actualOrder.getId(), Collections.singletonList(productDtoPercentage));
        assertNotNull(actualOrderPercentage);
        verify(percentageService, never()).calculate(any(), any(), any());
        verify(discountService, never()).calculate(any(), any(), any());

    }
}
