package com.example.mad_project.models;

import android.graphics.Bitmap;

public class ImageModelClass {
    private Bitmap image;

    public ImageModelClass(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
