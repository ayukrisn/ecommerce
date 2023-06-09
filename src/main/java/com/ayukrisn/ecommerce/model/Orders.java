package com.ayukrisn.ecommerce.model;

public class Orders {
    // Variables
    private int id;
    private int buyer; //Foreign key: Users
    private String note;
    private int total;
    private int discount;
    private int is_paid;

    // Constructor
    public Orders() {}
    public Orders(int id, int buyer, String note, int total, int discount, int is_paid) {
        this.id = id;
        this.note = note;
        this.total = total;
        this.discount = discount;
        this.is_paid = is_paid;
    }

    // Getter and setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getBuyer() {
        return buyer;
    }
    public void setBuyer(int buyer) {
        this.buyer = buyer;
    }

    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }

    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }

    public int getDiscount() {
        return discount;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getIs_paid() {
        return is_paid;
    }
    public void setIs_paid(int is_paid) {
        this.is_paid = is_paid;
    }
}
