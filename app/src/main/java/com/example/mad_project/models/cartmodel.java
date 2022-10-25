package com.example.mad_project.models;

public class cartmodel {
    int photo, quantity;
    String devicename, price;

    public cartmodel(int photo, int quantity, String devicename, String price) {
        this.photo = photo;
        this.quantity = quantity;
        this.devicename = devicename;
        this.price = price;
    }

    public int getPhoto() {
        return photo;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDevicename() {
        return devicename;
    }

    public String getPrice() {
        return price;
    }

    public void setPhoto(int photo) {                      
        this.photo = photo;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
