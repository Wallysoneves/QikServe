package com.br.qikserveteste.service;

import com.br.qikserveteste.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    Product getById(String id);
}
