package com.example.e_shop;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//Πινακας Cart
@Entity (tableName = "cart_table")
public class Cart {
    @PrimaryKey (autoGenerate = true) @ColumnInfo(name = "cid")
    private int cid;
    private String category;
    private String name;
    private String description;
    private double price;
    private int capacity;
//Δομητης
    public Cart(int cid, String category, String name, String description, double price, int capacity) {
        this.cid = cid;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.capacity = capacity;

    }
//getters - setters
    public int getCid() {
        return cid;
    }

    public void setCid(int id) {
        this.cid = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
