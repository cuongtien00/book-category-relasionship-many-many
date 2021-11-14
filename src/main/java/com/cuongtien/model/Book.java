package com.cuongtien.model;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private int id;
    private String name;
    private double price;
    private String des;
    private List<Category> categories;

    public Book() {
        categories = new ArrayList<>();
    }

    public Book(String name, double price, String des, List<Category> categories) {
        this.name = name;
        this.price = price;
        this.des = des;
        this.categories = categories;
    }

    public Book(int id, String name, double price, String des, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.des = des;
        this.categories = categories;

    }

    public Book(int id, String name, double price, String des) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.des = des;
    }

    public Book(String name, double price, String des) {
        this.name = name;
        this.price = price;
        this.des = des;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
