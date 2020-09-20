package ru.gb.trishkin.spring.mvc.utils;

import org.springframework.stereotype.Service;

import java.util.Map;


public class ProductFilter {
    private StringBuilder filterValue;

    public ProductFilter(Map<String, String> map) {
        this.filterValue = new StringBuilder();
        if(map.containsKey("min_price") && !map.get("min_price").isEmpty()){
            double minPrice = Double.parseDouble(map.get("min_price"));
            filterValue.append("&min_price=").append(minPrice);
        }
        if (map.containsKey("max_price") && !map.get("max_price").isEmpty()){
            double maxPrice = Double.parseDouble(map.get("max_price"));
            filterValue.append("&max_price=").append(maxPrice);
        }
    }

    public StringBuilder getFilterValue() {
        return filterValue;
    }
}
