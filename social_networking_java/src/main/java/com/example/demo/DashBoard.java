package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Vector;

public class DashBoard implements Initializable {

    @FXML
    private TableColumn<StatusObject, String> activityCol;

    @FXML
    private Button dashboardBtn;

    @FXML
    private TableColumn<StatusObject, ImageView> imageCol;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchTxt;

    @FXML
    private Button messageBtn;

    @FXML
    private Button postBtn;

    @FXML
    private Button signoutBtn;

    @FXML
    private TableView<StatusObject> statusTable;

    @FXML
    private TableColumn<StatusObject, String> timeCol;

    @FXML
    private TableColumn<StatusObject, String> userCol;

    @FXML
    private Label user_fullnameLabel;

    private final ObservableList<StatusObject> oblist = FXCollections.observableArrayList();

    @FXML
    void signoutOnClicked(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            Stage window = (Stage) signoutBtn.getScene().getWindow();
            window.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchBtnOnClicked(ActionEvent event) {
        User.usersearch = searchTxt.getText();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SearchResult.fxml"));
            Stage window = (Stage) searchBtn.getScene().getWindow();
            window.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void postBtnOnClicked(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Status.fxml"));
            Stage window = (Stage) signoutBtn.getScene().getWindow();
            window.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUserFullName(String username) {
        DatabaseConnection db = new DatabaseConnection();
        String user_fullname = db.getUserFullName_fromUsername(username);
        if (user_fullname == null) {
            user_fullname = "null";
        }
        user_fullnameLabel.setText("Hello, " + user_fullname) ;
        User.userfullname_ = user_fullname;
    }

    public void load_DashBoard(String Username) {
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<StatusObject> allStatus = db.loadStatus(Username);

        for (int i = 0; i < allStatus.size(); i++) {
            oblist.add(allStatus.get(i));
        }
        statusTable.setItems(oblist);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUserFullName(User.username_);

        // set up for the table
        statusTable.setColumnResizePolicy(new Callback<TableView.ResizeFeatures, Boolean>() {
            @Override
            public Boolean call(TableView.ResizeFeatures resizeFeatures) {
                return true;
            }
        });
        userCol.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        activityCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        imageCol.setCellValueFactory(new PropertyValueFactory<>("image"));

        load_DashBoard(User.username_);
    }
}
