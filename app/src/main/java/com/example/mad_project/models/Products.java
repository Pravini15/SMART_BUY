package com.example.mad_project.models;

public class Products {
    String name;
    String prize;
    String status;

    public Products(String name, String prize, String status) {
        this.name = name;
        this.prize = prize;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }
}
