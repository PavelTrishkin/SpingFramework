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

    public List<Product> findAll(){
        return productRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public Page<Product> getProductByPrice(Double startPrice, Double endPrice, int pageNum, int pageSize) {
        if (pageNum < 1) {
            pageNum = 1;
        }
        Page<Product> products = productRepo.findProductsByPriceBetween(startPrice, endPrice, PageRequest.of(pageNum - 1, pageSize));
        return products;
    }

    @Transactional
    public Product createOrSaveProduct(Product product) {
        return productRepo.save(product);
    }

    @Transactional
    public void removeProduct(Long id) {
        productRepo.deleteById(id);
    }

    public boolean existProduct(Long id){
        return productRepo.existsById(id);
    }
}
