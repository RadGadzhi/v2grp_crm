package com.example.demo.repository;

import com.example.demo.model.ProductAlias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductAliasRepository extends JpaRepository<ProductAlias, Long> {
    Optional<ProductAlias> findByAliasIgnoreCase(String alias);
}
