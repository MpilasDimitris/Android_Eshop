package com.example.e_shop;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//πινακας users
@Entity (tableName = "users")
public class User {
    //τα πεδια του πινακα
    @PrimaryKey (autoGenerate = true)
    private int id;
    @ColumnInfo(name = "users_name")
    private String name;
    @ColumnInfo(name = "users_lastname")
    private String lastName;
    @ColumnInfo (name = "users_mail")
    private String mail;
    @ColumnInfo (name = "password")
    private String password;
//δομητης
    public User(String name, String lastName, String mail, String password) {
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
    }
    //κενος δομητης
    public User(){}
    //setters-getters
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword(){return password;}

    public void setPassword(String password){ this.password = password;}
}
