package com.example.mad_project.models;

public class Products {
    String id;
    String name;
    String prize;

    public Products(String id, String name, String prize) {
        this.id = id;
        this.name = name;
        this.prize = prize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }
}
