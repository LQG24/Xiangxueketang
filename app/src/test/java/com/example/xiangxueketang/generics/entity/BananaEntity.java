package com.example.xiangxueketang.generics.entity;

public class BananaEntity {
    private String name;
    private int price;

    public BananaEntity(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BananaEntity{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
