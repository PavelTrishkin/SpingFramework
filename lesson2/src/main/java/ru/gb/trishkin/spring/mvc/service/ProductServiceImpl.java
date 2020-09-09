package ru.gb.trishkin.spring.mvc.service;

import org.springframework.stereotype.Service;
import ru.gb.trishkin.spring.mvc.domain.Product;
import ru.gb.trishkin.spring.mvc.repository.ProductRepo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl {
    private ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product getProductById(Long id) {
        return productRepo.getProductById(id);
    }

    public List<Product> getAllProduct() {
        List<Product> products = productRepo.getAllProduct();
        products.sort(Comparator.comparingLong(Product::getId));
        return products;
    }

    public List<Product> getProductByPrice(Double starPrice, Double endPrice) {
        return productRepo.getAllProduct().stream()
                .filter(product -> product.getPrice() >= starPrice && product.getPrice() <= endPrice)
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toList());
    }

    public Product createProduct(Product product){
        return productRepo.createProduct(product);
    }

    public void removeProduct(Long id){
        productRepo.removeProduct(id);
    }
}
