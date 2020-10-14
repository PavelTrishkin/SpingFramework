package ru.gb.trishkin.spring.mvc.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.trishkin.spring.mvc.domain.Product;
import ru.gb.trishkin.spring.mvc.exceptions.ProductNotFoundException;
import ru.gb.trishkin.spring.mvc.service.ProductServiceImpl;
import ru.gb.trishkin.spring.mvc.utils.ProductFilter;

import java.util.Map;

@Controller("productController")
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, @RequestParam Map<String, String> requestParams) {
        Integer pageNumber = Integer.parseInt(requestParams.getOrDefault("p", "1"));
        ProductFilter productFilter = new ProductFilter(requestParams);
        double minPrice = 0.0;
        double maxPrice = 999.99;
        if(requestParams.containsKey("min_price") && !requestParams.get("min_price").isEmpty()){
            minPrice = Double.parseDouble(requestParams.get("min_price"));
            System.out.println("minPrice " + minPrice);
        }
        if (requestParams.containsKey("max_price") && !requestParams.get("max_price").isEmpty()){
            maxPrice = Double.parseDouble(requestParams.get("max_price"));
            System.out.println("maxPrice " + maxPrice);
        }
        Page<Product> products = productService.getProductByPrice(minPrice,maxPrice,pageNumber,5);
        model.addAttribute("products", products);
        model.addAttribute("filterDef", productFilter.getFilterValue().toString());
        return "product";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(Model model,@PathVariable("id") Long id){
        Product byId = productService.findById(id);
        model.addAttribute("product",
                byId == null ? new Product(): byId);
        return "product-info";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        System.out.println(productService.findById(id).getTitle());
        model.addAttribute("product", productService.findById(id));
        return "edit_product";
    }

    @PostMapping("/edit")
    public String modifyProduct(Product product) {
        productService.createOrSaveProduct(product);
        return "redirect:/products/";
    }

    @GetMapping("/new")
    public String getNewForm(Model model) {
        model.addAttribute("product", new Product());
        return "new-product";
    }

    @PostMapping("/new")
    public String addNewProduct(Product product) {
        Product savedProduct = productService.createOrSaveProduct(product);
        System.out.println(savedProduct);
        return "redirect:/products/" + savedProduct.getId();
    }

}
