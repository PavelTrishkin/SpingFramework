package ru.gb.trishkin.spring.mvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.trishkin.spring.mvc.domain.Product;
import ru.gb.trishkin.spring.mvc.exceptions.ProductNotFoundException;
import ru.gb.trishkin.spring.mvc.service.ProductServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class RestProductController {

    private final ProductServiceImpl productService;

    public RestProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findProductById(@PathVariable Long id) {
        if (!productService.existProduct(id)) {
            throw new ProductNotFoundException("Product not found with this ID: " + id);
        }
        return new ResponseEntity<>(productService.findById(id), HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        if (!productService.existProduct(id)) {
            throw new ProductNotFoundException("Product not found with this ID: " + id);
        }
        productService.removeProduct(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createNewProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            product.setId(null);
        }
        return productService.createOrSaveProduct(product);
    }

    @PutMapping
    public ResponseEntity<?> modifyProduct(@RequestBody Product product) {
        if (product.getId() == null || !productService.existProduct(product.getId())) {
            throw new ProductNotFoundException("Product not found " + product.getId());
//            return new ResponseEntity<>("Product not found" + product.getId(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productService.createOrSaveProduct(product), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<?> exceptionHandle(ProductNotFoundException exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
    }
}
