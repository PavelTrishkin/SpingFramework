package ru.gb.trishkin.spring.mvc.repository;

import org.springframework.stereotype.Repository;
import ru.gb.trishkin.spring.mvc.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepo {

    private Map<Long, Product> rep = new HashMap<>();
    private long ind = 0;

    {
        rep.put(++ind , new Product(ind, "Milk", 65.50));
        rep.put(++ind, new Product(ind, "Bread", 38.99));
    }

    public Product getProductById (Long id){
        return rep.get(id);
    }
    public List<Product> getAllProduct() {
        return new ArrayList<>(rep.values());
    }

    public Product createProduct (Product product){
        if (product.getId() == null){
            product.setId(++ind);
        }
        rep.put(product.getId(), product);
        return product;
    }

    public void removeProduct (Long id){
        rep.remove(id);
    }
}
