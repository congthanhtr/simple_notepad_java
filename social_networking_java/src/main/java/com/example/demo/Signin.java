package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Signin {

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private Button signinBtn;

    @FXML
    private TextField usernameTxt;

    @FXML
    void signinOnclicked(ActionEvent event) {
        String username = usernameTxt.getText();
        String password = passwordTxt.getText();
//        if (username.equals("1") && password.equals("1")) {
//            Parent root;
//            try {
//                root = FXMLLoader.load(getClass().getResource("DashBoard.fxml"));
//                User.username = username;
//                System.out.println(User.username);
//                Stage window = (Stage) signinBtn.getScene().getWindow();
//                window.setScene(new Scene(root));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Account validation failed");
//            alert.setContentText("Username or Password is wrong. Try again!");
//            alert.showAndWait();
//        }
        DatabaseConnection db = new DatabaseConnection();
        boolean accountvalidation = db.accountValidation(username, password);
        if (accountvalidation == true) {
            Parent root;
            try {
                User.setUsername_(username);
                root = FXMLLoader.load(getClass().getResource("DashBoard.fxml"));
                Stage window = (Stage) signinBtn.getScene().getWindow();
                window.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Account validation failed");
            alert.setContentText("Username or Password is wrong. Try again!");
            alert.showAndWait();
        }
    }

}
