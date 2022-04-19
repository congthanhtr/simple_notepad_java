package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StatusObject {
    ImageView image;
    SimpleStringProperty user_id;
    SimpleStringProperty time;
    SimpleStringProperty status;

    StatusObject(String user_id, String time, String status, ImageView image) {
        this.user_id = new SimpleStringProperty(user_id);
        this.time = new SimpleStringProperty(time);
        this.status = new SimpleStringProperty(status);
        this.image = image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public String getUser_id() {
        return user_id.get();
    }

    public SimpleStringProperty user_idProperty() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id.set(user_id);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public ImageView getImage() {
        return image;
    }
}
