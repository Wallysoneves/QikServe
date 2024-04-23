package com.br.qikserveteste.service.implementation;

import com.br.qikserveteste.domain.Product;
import com.br.qikserveteste.infrastructure.exception.QikServeException;
import com.br.qikserveteste.infrastructure.route.WireMockProduct;
import com.br.qikserveteste.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private WireMockProduct wireMockProduct;

    @Override
    public List<Product> getAll() {
        try {
            return wireMockProduct.getAll().getBody();
        } catch(Exception e) {
            throw new QikServeException(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Product getById(String id) {
        try {
            return wireMockProduct.getById(id).getBody();
        } catch (Exception e) {
            throw new QikServeException(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
