package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Handler;

public class SearchResult implements Initializable {

    @FXML
    private Button backBtn;

    @FXML
    private Button dashboardBtn;

    @FXML
    private TableColumn<String, String> userCol;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button messagebtn;

    @FXML
    private Button searchBtn;

    @FXML
    private ListView<String> userList;


    @FXML
    private TextField searhTxt;

    @FXML
    private TableView<ObservableList<StringProperty>> userTable;

    @FXML
    private Text user_fullnameLab;

    private ObservableList<StringProperty> obl = FXCollections.observableArrayList();

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

    @FXML
    void dashboardOnClicked(ActionEvent event) {

    }

    @FXML
    void logoutOnClicked(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            Stage window = (Stage) logoutBtn.getScene().getWindow();
            window.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void messageOnClicked(ActionEvent event) {

    }

    @FXML
    void itemOnMouseClicked(MouseEvent event) {
        String username = userList.getSelectionModel().getSelectedItem();
        User.usersearch = username;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Wall.fxml"));
            Stage window = (Stage) backBtn.getScene().getWindow();
            window.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchOnClicked(ActionEvent event) {
        User.usersearch = searhTxt.getText();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SearchResult.fxml"));
            Stage window = (Stage) searchBtn.getScene().getWindow();
            window.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        load_searchResult(User.usersearch);
        searhTxt.setText(User.usersearch);
        user_fullnameLab.setText("Hello, " + User.userfullname_);
    }

    public void load_searchResult(String username) {
        DatabaseConnection db = new DatabaseConnection();
        userList.getItems().add(db.searchUser(username));
    }
}
