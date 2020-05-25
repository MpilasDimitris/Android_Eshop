package com.example.e_shop;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;
//Πινακασ Sales
//Βαζω τα κυρια και ξενα κλειδια απο του πινακες που θελω
@Entity (tableName = "sales",
        foreignKeys = {
        @ForeignKey(entity = User.class,
        parentColumns = "id",
        childColumns = "suid",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE),
        @ForeignKey(entity = Products.class,
        parentColumns = "pid",
        childColumns = "scid",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)})

public class Sales {
//Δηλωω τις στηλες του πινακα
    @ColumnInfo(name = "suid")
    @NonNull
    private int id;

    @ColumnInfo(name = "scid")
    @NonNull
    private int cid;
   @ColumnInfo(name = "sdate")
    @NonNull
    private String date;
   @ColumnInfo(name = "sales_id")
   @NonNull
   @PrimaryKey (autoGenerate = true)
   private int salesid;



    private String product_name;
    private  int product_capacity;
    private double total_price;
//δομητης
    public Sales(int id, int cid, String date, String product_name, int product_capacity, double total_price) {
        this.id = id;
        this.cid = cid;
        this.date = date;

        this.product_name = product_name;
        this.product_capacity = product_capacity;
        this.total_price = total_price;
    }
//setters-getters

    public int getSalesid() {
        return salesid;
    }

    public void setSalesid(int salesid) {
        this.salesid = salesid;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }


    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_capacity() {
        return product_capacity;
    }

    public void setProduct_capacity(int product_capacity) {
        this.product_capacity = product_capacity;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int pid) {
        this.cid = cid;
    }

}





