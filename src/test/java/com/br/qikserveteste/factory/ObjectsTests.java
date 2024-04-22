package com.br.qikserveteste.factory;

import com.br.qikserveteste.domain.Product;
import com.br.qikserveteste.domain.dto.ItemDto;
import com.br.qikserveteste.domain.dto.OrderDto;
import com.br.qikserveteste.domain.dto.ProductDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ObjectsTests {

    /*-----------------------------------------ORDER WITH OUT PROMOTION-----------------------------------------*/

    public static Product createProduct() {
        return new Product("4MB7UfpTQs", "Boring Fries!", new BigDecimal(199), new ArrayList<>());
    }

    public static ItemDto createItemDto() {
        return new ItemDto("4MB7UfpTQs", "Boring Fries!", 1, new BigDecimal(199), BigDecimal.ZERO, new BigDecimal(199));
    }

    public static ProductDto createProductDto() {
        return new ProductDto("4MB7UfpTQs", 1);
    }

    public static OrderDto createOrderDto() {
        ItemDto itemDto = createItemDto();
        return new OrderDto(Collections.singletonList(itemDto), new BigDecimal(199).setScale(2), BigDecimal.ZERO);
    }

    /*-----------------------------------------ORDER WITH PROMOTION GIFT-----------------------------------------*/

    public static Product createProductGift() {
        return new Product("PWWe3w1SDU", "Amazing Burger!", new BigDecimal(999).setScale(2), new ArrayList<>());
    }

    public static ItemDto createItemDtoGift() {
        return new ItemDto("PWWe3w1SDU", "Amazing Burger!", 3, new BigDecimal(999).setScale(2), new BigDecimal(1998).setScale(2), new BigDecimal(2997).setScale(2));
    }

    public static ProductDto createProductDtoGift() {
        return new ProductDto("PWWe3w1SDU", 3);
    }

    public static OrderDto createOrderDtoGift() {
        ItemDto itemDto = createItemDtoGift();
        return new OrderDto(Collections.singletonList(itemDto), itemDto.getSubTotal().setScale(2), itemDto.getDiscount());
    }

    /*-----------------------------------------ORDER WITH PROMOTION DISCOUNT-----------------------------------------*/

    public static Product createProductDiscount() {
        return new Product("Dwt5F7KAhi", "Amazing Pizza!", new BigDecimal(1099).setScale(2), new ArrayList<>());
    }

    public static ItemDto createItemDtoDiscount() {
        return new ItemDto("Dwt5F7KAhi", "Amazing Pizza!", 3, new BigDecimal(1099).setScale(2), new BigDecimal(2898).setScale(2), new BigDecimal(3297).setScale(2));
    }

    public static ProductDto createProductDtoDiscount() {
        return new ProductDto("Dwt5F7KAhi", 3);
    }

    public static OrderDto createOrderDtoDiscount() {
        ItemDto itemDto = createItemDtoDiscount();
        return new OrderDto(Collections.singletonList(itemDto), itemDto.getSubTotal().setScale(2), itemDto.getDiscount());
    }

    /*-----------------------------------------ORDER WITH PROMOTION PERCENTAGE-----------------------------------------*/

    public static Product createProductPercentage() {
        return new Product("C8GDyLrHJb", "Amazing Salad!", new BigDecimal(499).setScale(2), new ArrayList<>());
    }

    public static ItemDto createItemDtoPercentage() {
        return new ItemDto("C8GDyLrHJb", "Amazing Salad!", 5, new BigDecimal(499).setScale(2), new BigDecimal(2245.5).setScale(2), new BigDecimal(2495).setScale(2));
    }

    public static ProductDto createProductDtoPercentage() {
        return new ProductDto("C8GDyLrHJb", 5);
    }

    public static OrderDto createOrderDtoPercentage() {
        ItemDto itemDto = createItemDtoPercentage();
        return new OrderDto(Collections.singletonList(itemDto), itemDto.getSubTotal().setScale(2), itemDto.getDiscount());
    }

    /*-----------------------------------------ORDER WITH PROMOTION ALL-----------------------------------------*/

    public static List<Product> createProductAll() {
        return List.of(
                new Product("C8GDyLrHJb", "Amazing Salad!", new BigDecimal(499).setScale(2), new ArrayList<>()),
                new Product("Dwt5F7KAhi", "Amazing Pizza!", new BigDecimal(1099).setScale(2), new ArrayList<>()),
                new Product("PWWe3w1SDU", "Amazing Burger!", new BigDecimal(999).setScale(2), new ArrayList<>())
        );

    }

    public static List<ItemDto> createItemDtoAll() {
        return List.of(
                new ItemDto("C8GDyLrHJb", "Amazing Salad!", 5, new BigDecimal(499).setScale(2), new BigDecimal(2245.5).setScale(2), new BigDecimal(2495).setScale(2)),
                new ItemDto("Dwt5F7KAhi", "Amazing Pizza!", 3, new BigDecimal(1099).setScale(2), new BigDecimal(2898).setScale(2), new BigDecimal(3297).setScale(2)),
                new ItemDto("PWWe3w1SDU", "Amazing Burger!", 3, new BigDecimal(999).setScale(2), new BigDecimal(1998).setScale(2), new BigDecimal(2997).setScale(2))
        );
    }

    public static List<ProductDto> createProductDtoAll() {
        return List.of(
                new ProductDto("C8GDyLrHJb", 5),
                new ProductDto("Dwt5F7KAhi", 3),
                new ProductDto("PWWe3w1SDU", 3)
        );
    }

    public static OrderDto createOrderDtoAll() {
        List<ItemDto> itemsDto = createItemDtoAll();
        return new OrderDto(itemsDto, new BigDecimal(8789).setScale(2), new BigDecimal(7141.5).setScale(2));
    }
}
