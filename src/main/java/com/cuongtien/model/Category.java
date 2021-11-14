package com.cuongtien.model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private int id;
    private String name;
    private String des;
    private List<Book> books ;

    public Category() {
        books = new ArrayList<>();
    }

    public Category(int id, String name, String des) {
        this.id = id;
        this.name = name;
        this.des = des;
    }

    public Category(int id, String name, String des, List<Book> books) {
        this.id = id;
        this.name = name;
        this.des = des;
        this.books = books;
    }

    public Category(String name, String des, List<Book> books) {
        this.name = name;
        this.des = des;
        this.books = books;
    }

    public Category(String name, String des) {
        this.name = name;
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", des='" + des + '\'' +
                ", books=" + books +
                '}';
    }
}
