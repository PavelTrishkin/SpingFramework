package ru.gb.trishkin.spring.mvc.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.trishkin.spring.mvc.domain.Product;
import ru.gb.trishkin.spring.mvc.repository.ProductRepo;

import java.util.List;

@Service
public class ProductServiceImpl {
    private ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepo.findProductById(id);
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }

    @Transactional(readOnly = true)
    public List<Product> getProductByPrice(Double startPrice, Double endPrice) {
        return productRepo.findProductsByPriceBetween(startPrice, endPrice);
    }

    @Transactional
    public  Page<Product> getProductDescSort(){
        Pageable pageable = PageRequest.of(0,5, Sort.by("price").descending());
        return productRepo.findAll(pageable);
    }

    @Transactional
    public  Page<Product> getProductAscSort(){
        Pageable pageable = PageRequest.of(0,5, Sort.by("price").ascending());
        return productRepo.findAll(pageable);
    }

    @Transactional
    public Product createProduct(Product product){
        return productRepo.save(product);
    }

    @Transactional
    public void removeProduct(Long id){
        productRepo.deleteById(id);
    }
}
