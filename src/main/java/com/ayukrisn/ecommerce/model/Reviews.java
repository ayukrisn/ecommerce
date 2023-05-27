package com.ayukrisn.ecommerce.model;

import java.awt.*;

public class Reviews {
    // Variables
    private int order; //Foreign key: orders
    private int star;
    private String description;

    // Constructor
    public Reviews (int order, int star, String description) {
        this.order = order;
        this.star = star;
        this.description = description;
    }
}
