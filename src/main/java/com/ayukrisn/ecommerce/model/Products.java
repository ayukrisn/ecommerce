package com.ayukrisn.ecommerce.model;

public class Products {
    //Variable
    private int id;
    private int seller;
    private String title;
    private String description;
    private int price;
    private int stock;

    // Constructor
    public Products(int id, int seller, String title, String description, int price, int stock) {
        this.id = id;
        this.seller = seller; //Foreign key: Users
        this.title = title;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    // Getter and Setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getSeller() {
        return seller;
    }
    public void setSeller(int seller) {
        this.seller = seller;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}
