package ru.gb.trishkin.spring.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.trishkin.spring.mvc.domain.Product;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository <Product, Long> {
    Product findProductById(Long id);
    List<Product> findProductsByPriceBetween(Double startPrice, Double endPrice);
}
