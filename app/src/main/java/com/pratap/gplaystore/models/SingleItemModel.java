package com.pratap.gplaystore.models;

import android.graphics.Bitmap;

/**
 * Created by pratap.kesaboyina on 01-12-2015.
 */
public class SingleItemModel {


    private String name;
    private String image_url;
    private String description;
    private Bitmap image;




    public SingleItemModel() {
    }

    public SingleItemModel(String name, String image_url) {
        this.name = name;
        this.image_url = image_url;
    }


    public String getUrl() {
        return image_url;
    }

    public void setUrl(String image_url) {
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

}
