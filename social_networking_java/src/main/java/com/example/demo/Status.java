package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class Status {

    @FXML
    private Button backBtn;

    @FXML
    private ImageView plusImg;

    @FXML
    private ImageView imageUploader;

    @FXML
    private Button postBtn;

    @FXML
    private TextArea statusTxt;

    @FXML
    private Button uploadBtn;

    @FXML
    void backOnClicked(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DashBoard.fxml"));
            Stage window = (Stage) backBtn.getScene().getWindow();
            window.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // filter file extension: like .jpg, .png
    @FXML
    void imageUploaderOnClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

        File file = fileChooser.showOpenDialog(null);
        imageUploader.setImage(new Image(file.toURI().toString()));
        plusImg.setVisible(false);
    }

    @FXML
    void postOnClicked(ActionEvent event) {
        String status = statusTxt.getText();
        String image_path = "";
        if (imageUploader.getImage()!=null) {
            image_path = (imageUploader.getImage().getUrl());
        }

        DatabaseConnection db = new DatabaseConnection();
        db.addStatus(User.username_, status, image_path);

        statusTxt.setText("");
        if (imageUploader.getImage()!=null) {
            imageUploader.setVisible(false);
        }
    }
}
