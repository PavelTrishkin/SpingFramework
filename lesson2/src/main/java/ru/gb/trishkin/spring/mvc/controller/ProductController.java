package ru.gb.trishkin.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.trishkin.spring.mvc.domain.Product;
import ru.gb.trishkin.spring.mvc.service.ProductServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(Model model,@PathVariable("id") Long id){
        Product byId = productService.getProductById(id);
        model.addAttribute("product",
                byId == null ? new Product(): byId);
        return "product";
    }

    @GetMapping("/new")
    public String getNewForm(Model model){
        model.addAttribute("product", new Product());
        return "new-product";
    }

    @PostMapping("/new")
    public String addNewProduct(Product product){
        Product savedProduct = productService.createProduct(product);
        System.out.println(savedProduct);
        return "redirect:/products/" + savedProduct.getId();
    }

}