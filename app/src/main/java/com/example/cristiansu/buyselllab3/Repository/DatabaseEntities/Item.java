package com.example.cristiansu.buyselllab3.Repository.DatabaseEntities;

import java.io.Serializable;

/**
 * Created by CristianSu on 12/5/2016.
 */

public class Item implements Serializable   {
    private long id;
    private long user_id;
    private String name;
    private double price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
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
}
