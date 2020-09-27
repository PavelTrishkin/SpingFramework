package ru.gb.trishkin.spring.mvc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.trishkin.spring.mvc.domain.Product;

@Repository
public interface ProductRepo extends JpaRepository <Product, Long>{
    Page<Product> findProductsByPriceBetween(Double startPrice, Double endPrice, Pageable pageable);
}
