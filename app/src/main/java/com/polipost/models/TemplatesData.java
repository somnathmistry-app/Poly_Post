package com.polipost.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TemplatesData {
    @Expose
    @SerializedName("updated_at")
    private String updatedAt;
    @Expose
    @SerializedName("image")
    private String image;
    @Expose
    @SerializedName("category")
    private String category;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("json_img")
    private int json_img;

    public int getJson_img() {
        return json_img;
    }

    public void setJson_img(int json_img) {
        this.json_img = json_img;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
