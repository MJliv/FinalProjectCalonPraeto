package com.example.myapplication;

public class ImageModel {
    String desc, photo;

    public ImageModel() {
    }

    public ImageModel(String desc, String photo) {
        this.desc = desc;
        this.photo = photo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
