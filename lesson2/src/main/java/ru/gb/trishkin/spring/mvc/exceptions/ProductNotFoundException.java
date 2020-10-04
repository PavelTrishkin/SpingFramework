package ru.gb.trishkin.spring.mvc.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String msg){super(msg);}
}
