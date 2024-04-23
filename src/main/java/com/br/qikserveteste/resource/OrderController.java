package com.br.qikserveteste.resource;

import com.br.qikserveteste.domain.dto.OrderDto;
import com.br.qikserveteste.domain.dto.ProductDto;
import com.br.qikserveteste.infrastructure.exception.ErroModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tags({
        @Tag(name = "order", description = "documentation for the requested resource")
})
public interface OrderController {

    @Operation(
            summary = "create an order",
            description = "Method responsible for creating an order in the system",
            tags = { "order"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = OrderDto.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "303", content = { @Content(schema = @Schema(implementation = ErroModel.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ErroModel.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = ErroModel.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    ResponseEntity<OrderDto> create(@RequestBody List<ProductDto> productsDto);

    @Operation(
            summary = "Retrieves an order",
            description = "Responsible method for retrieving an order in the system",
            tags = { "order" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = OrderDto.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "303", content = { @Content(schema = @Schema(implementation = ErroModel.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ErroModel.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = ErroModel.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}) }
    )
    ResponseEntity<OrderDto> getById(@PathVariable("id") @Validated String id);

    @Operation(
            summary = "Change an order",
            description = "Method responsible for changing an order in the system",
            tags = { "order" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = OrderDto.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "303", content = { @Content(schema = @Schema(implementation = ErroModel.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ErroModel.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = ErroModel.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}) }
    )
    ResponseEntity<OrderDto> update(@PathVariable("id") @Validated String id, @RequestBody List<ProductDto> productsDto);

}
