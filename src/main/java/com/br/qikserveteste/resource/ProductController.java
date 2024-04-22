package com.br.qikserveteste.resource;

import com.br.qikserveteste.domain.Product;
import com.br.qikserveteste.infrastructure.exception.ErroModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductController {

    @Operation(
            summary = "Retrieves multiple products",
            description = "Responsible method for recovering multiple products on the system",
            tags = { "product" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Product.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "303", content = { @Content(schema = @Schema(implementation = ErroModel.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ErroModel.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = ErroModel.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}) }
    )
    ResponseEntity<List<Product>> getAll();

    @Operation(
            summary = "Retrieves an product",
            description = "Responsible method for retrieving an product in the system",
            tags = { "product" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Product.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "303", content = { @Content(schema = @Schema(implementation = ErroModel.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ErroModel.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = ErroModel.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}) }
    )
    ResponseEntity<Product> getById(@PathVariable("id") String id);
}
