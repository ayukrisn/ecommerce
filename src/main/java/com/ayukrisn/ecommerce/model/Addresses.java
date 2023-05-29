package com.ayukrisn.ecommerce.model;

public class Addresses {
    public enum Type {
        office, home
    }

    // Variables
    private int user; //Foreign key: Users
    private Type type;
    private String line1;
    private String line2;
    private String city;
    private String province;
    private String postcode;

    // Constructor
    public Addresses(){}
    public Addresses(int users, Type type, String line1, String line2, String city, String province, String postcode) {
        this.type = type;
        this.user = users;
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.province = province;
        this.postcode = postcode;
    }

    // Getter and Setter
    public int getUser() {
        return user;
    }
    public void setUser(int user) {
        this.user = user;
    }

    public Type getType() {
        return type;
    }
    public void setType(String type) {
        this.type = Type.valueOf(type);
    }

    public String getLine1() {
        return line1;
    }
    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }
    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

}
