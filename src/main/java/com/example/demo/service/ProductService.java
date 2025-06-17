package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.model.ProductAlias;
import com.example.demo.repository.ProductAliasRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductAliasRepository productAliasRepository;

    public ProductService(ProductRepository productRepository, ProductAliasRepository productAliasRepository) {
        this.productRepository = productRepository;
        this.productAliasRepository = productAliasRepository;
    }

    /**
     * Получить нормализованный товар по названию (через алиас)
     */
    public Optional<Product> findProductByAnyName(String name) {
        // 1. Пытаемся найти по основному названию
        Optional<Product> product = productRepository.findByNameIgnoreCase(name);
        if (product.isPresent()) return product;

        // 2. Иначе ищем среди алиасов
        Optional<ProductAlias> alias = productAliasRepository.findByAliasIgnoreCase(name);
        return alias.map(ProductAlias::getProduct);
    }

    /**
     * Создание нового товара с основным названием и возможным алиасом
     */
    public Product createProductWithAlias(String officialName, String code, String unit, String... aliases) {
        Product product = new Product();
        product.setName(officialName);
        product.setCode(code);
        product.setUnit(unit);
        product = productRepository.save(product);

        for (String alias : aliases) {
            ProductAlias a = new ProductAlias();
            a.setAlias(alias);
            a.setProduct(product);
            productAliasRepository.save(a);
        }

        return product;
    }
}
