package com.ayukrisn.ecommerce.model;

public class Reviews {
    // Variables
    private int order; //Foreign key: order
    private int star;
    private String description;

    // Constructor
    public Reviews() {}
    public Reviews(int order, int star, String description) {
        this.order = order;
        this.star = star;
        this.description = description;
    }

    // Getter and setter
    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }

    public int getStar() {
        return star;
    }
    public void setStar(int star) {
        this.star = star;
    }
}
