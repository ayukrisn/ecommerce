package com.ayukrisn.ecommerce.model;

public class Users {

    // Variables
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    public static enum Type {buyer, seller}
    private Type type;

    // Constructor
    public Users () {

    }
    public Users(int id, String first_name, String last_name, String email, String phone_number, String type){
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.type = Type.valueOf(type);
    }

    // Getter and Setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Type getType() {
        return type;
    }
    public void setType(String type) {
        this.type = Type.valueOf(type);
    }
}
