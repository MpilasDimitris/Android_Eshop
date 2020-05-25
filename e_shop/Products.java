package com.example.e_shop;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//Πινακας Products
@Entity (tableName = "products")
public class Products {
    @PrimaryKey (autoGenerate = true)  @ColumnInfo (name = "pid")
    private int id;
@ColumnInfo(name = "product_category")
    private String category;
   @ColumnInfo(name = "pname")
    private String name;
    private String description;
    private double price;
    private int capacity;
//Δομητης
    public Products(String category, String name, String description, double price,int capacity) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.capacity = capacity;
    }
    //κενος δομητης
    public Products(){}


    //setters-getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
