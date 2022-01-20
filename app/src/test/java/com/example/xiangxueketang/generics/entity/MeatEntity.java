package com.example.xiangxueketang.generics.entity;

public class MeatEntity {
    private String name;
    private int price;

    public MeatEntity(String name, int price) {
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
        return "MeatEntity{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
