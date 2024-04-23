package com.br.qikserveteste.service.implementation;

import com.br.qikserveteste.domain.Product;
import com.br.qikserveteste.domain.Promotion;
import com.br.qikserveteste.domain.dto.ItemDto;
import com.br.qikserveteste.domain.dto.OrderDto;
import com.br.qikserveteste.domain.dto.ProductDto;
import com.br.qikserveteste.domain.enums.TypePromotion;
import com.br.qikserveteste.infrastructure.cache.OrderCache;
import com.br.qikserveteste.infrastructure.exception.QikServeException;
import com.br.qikserveteste.service.OrderService;
import com.br.qikserveteste.service.ProductService;
import com.br.qikserveteste.service.PromotionStrategy;
import com.br.qikserveteste.service.strategy.DiscountService;
import com.br.qikserveteste.service.strategy.GiftService;
import com.br.qikserveteste.service.strategy.PercentageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderCache orderCache;

    private final Map<TypePromotion, PromotionStrategy> typePromotionMap = Map.of(
            TypePromotion.FLAT_PERCENT, new PercentageService(),
            TypePromotion.BUY_X_GET_Y_FREE, new GiftService(),
            TypePromotion.QTY_BASED_PRICE_OVERRIDE, new DiscountService()
    );

    @Override
    public OrderDto create(List<ProductDto> productsDto) {

        if (productsDto.isEmpty()) {
            throw new QikServeException("You must pass at least one product to create an order", HttpStatus.BAD_REQUEST);
        }

        OrderDto order = this.buildOrder(productsDto);
        orderCache.saveOrder(order.getId(), order);

        return order;
    }

    @Override
    public OrderDto getById(String id) {
        return orderCache.getOrderById(id).orElseThrow(() -> new QikServeException("order not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public OrderDto update(String id, List<ProductDto> products) {

        if (products.isEmpty()) {
            throw new QikServeException("invalid products", HttpStatus.BAD_REQUEST);
        }

        OrderDto order = this.getById(id);

        List<ProductDto> productDtosGroup = this.groupItemsWithProduct(products,order.getItems());

        OrderDto orderNew = this.buildOrder(productDtosGroup);
        orderNew.setId(id);

        this.orderCache.removeOrder(id);
        this.orderCache.saveOrder(id, orderNew);

        return orderNew;
    }

    private List<ProductDto> groupItemsWithProduct(List<ProductDto> productDtos, List<ItemDto> itemDtos) {
        Map<String, Integer> groupedQuantities = itemDtos.stream()
                .collect(Collectors.groupingBy(ItemDto::getId, Collectors.summingInt(ItemDto::getQty)));

        List<ProductDto> ProductsDtoNew = new ArrayList<>();

        productDtos.forEach(productDto -> {
            Integer qtyNew = productDto.qty();
            if (groupedQuantities.containsKey(productDto.id())) {
                qtyNew += groupedQuantities.get(productDto.id());
                groupedQuantities.remove(productDto.id());
            }
            ProductsDtoNew.add(new ProductDto(productDto.id(), qtyNew));
        });

        groupedQuantities.forEach((id, qty) -> {
            ProductsDtoNew.add(new ProductDto(id, qty));
        });

        return ProductsDtoNew;
    }

    private OrderDto buildOrder(List<ProductDto> productsDto) {
        try {
            OrderDto order = new OrderDto();

            for (ProductDto productDto: productsDto) {
                Product product = productService.getById(productDto.id());

                Promotion promotion = product.getPromotions().stream().findFirst().orElse(null);

                if (Objects.nonNull(promotion)) {
                    order.getItems().add(typePromotionMap.get(promotion.getType()).calculate(productDto, product, promotion));
                } else {
                    BigDecimal totalItem = product.getPrice().multiply(new BigDecimal(productDto.qty())).setScale(2, BigDecimal.ROUND_HALF_UP);

                    order.getItems().add(ItemDto.builder()
                            .id(productDto.id())
                            .name(product.getName())
                            .qty(productDto.qty())
                            .price(product.getPrice())
                            .discount(BigDecimal.ZERO)
                            .subTotal(totalItem)
                            .build());
                }
            }

            BigDecimal total = order.getItems().parallelStream().filter(Objects::nonNull).map(ItemDto::getSubTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal totalDiscount = order.getItems().parallelStream().filter(Objects::nonNull).map(ItemDto::getDiscount).reduce(BigDecimal.ZERO, BigDecimal::add);

            order.setTotal(total);

            if (totalDiscount.compareTo(BigDecimal.ZERO) > 0) {
                order.setTotalDiscount(total.subtract(totalDiscount));
            }

            return order;

        } catch (Exception e) {
            throw new QikServeException(e.getLocalizedMessage());
        }
    }
}
